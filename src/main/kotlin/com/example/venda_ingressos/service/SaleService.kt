package com.example.venda_ingressos.service

import com.example.venda_ingressos.controller.request.paged.PagedRequest
import com.example.venda_ingressos.controller.request.SaleRequest
import com.example.venda_ingressos.controller.response.SaleResponse
import com.example.venda_ingressos.entities.Sale
import com.example.venda_ingressos.entities.Session
import com.example.venda_ingressos.mapper.SaleMapper
import com.example.venda_ingressos.repository.SaleRepository
import com.example.venda_ingressos.repository.SessionRepository
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class SaleService(
    val repository: SaleRepository,
    val sessionRepository: SessionRepository,
    val clientService: ClientService,
    val mapper: SaleMapper
) {

    @Transactional
    fun generateSale(request: SaleRequest): Sale {
        val sessionEntity = sessionRepository.findByName(request.nameOfSession)

        // TODO mais pra frente fazer um tratamento de erros personalizado para os throw
        if (sessionEntity.quantityTickets == 0 || request.numberOfTickets > sessionEntity.quantityTickets) {
            throw Exception("Não tem mais ingressos suficientes")
        }

        if (request.clients!!.size != request.numberOfTickets) {
            throw Exception("O numero de clientes e o numero de ingressos comprados não batem.")
        }

        sessionEntity.quantityTickets -= request.numberOfTickets
        sessionEntity.valueOfTickets = BigDecimal(500)
        sessionRepository.save(sessionEntity)

        val entity = generateSaleEntity(sessionEntity, request, sessionEntity.valueOfTickets!!)
        repository.save(entity)

        clientService.saveClientBySale(request, entity)

        return entity
    }

    fun findAll(pagedRequest: PagedRequest): Page<SaleResponse> {
        return repository.findAll(pagedRequest.pageable()).map { mapper.entityToResponse(it) }
    }

    private fun generateSaleEntity(sessionEntity: Session, request: SaleRequest, totalValue: BigDecimal): Sale {
        return Sale(
            nameOfSession = sessionEntity.name,
            numberOfTickets = request.numberOfTickets,
            totalValue = totalValue.multiply(BigDecimal.valueOf(request.numberOfTickets.toLong())),
            session = sessionEntity
        )
    }

}