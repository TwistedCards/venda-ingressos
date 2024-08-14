package com.example.venda_ingressos.service

import com.example.venda_ingressos.dto.SaleDto
import com.example.venda_ingressos.entities.Sale
import com.example.venda_ingressos.entities.Session
import com.example.venda_ingressos.repository.SaleRepository
import com.example.venda_ingressos.repository.SessionRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class SaleService(
    val repository: SaleRepository,
    val sessionRepository: SessionRepository,
    val clientService: ClientService
) {

    @Transactional
    fun generateSale(saleDto: SaleDto): Sale {
        val totalValue = BigDecimal.valueOf(500)
        val sessionEntity = sessionRepository.findByName(saleDto.nameSession)

        if (sessionEntity.quantityTickets == 0 || saleDto.numberOfTickets > sessionEntity.quantityTickets) {
            throw Exception("Não tem mais ingressos suficientes")
        }

        if(saleDto.clients.size != saleDto.numberOfTickets) {
            throw Exception("O numero de clientes e o numero de ingressos comprados não batem.")
        }

        sessionEntity.quantityTickets -= saleDto.numberOfTickets
        sessionRepository.save(sessionEntity)

        val entity = generateSaleEntity(sessionEntity, saleDto, totalValue)
        repository.save(entity)

        clientService.saveClient(saleDto, entity)

        return entity
    }

    fun getAll(): List<Sale> {
        return repository.findAll()
    }

    private fun generateSaleEntity(sessionEntity: Session, saleDTO: SaleDto, totalValue: BigDecimal): Sale {
        return Sale(
            nameOfSession = sessionEntity.name,
            numberOfTickets = saleDTO.numberOfTickets,
            totalValue = totalValue.multiply(BigDecimal.valueOf(saleDTO.numberOfTickets.toLong())),
            session = sessionEntity
        )
    }

}