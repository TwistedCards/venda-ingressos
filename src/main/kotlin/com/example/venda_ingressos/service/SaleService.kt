package com.example.venda_ingressos.service

import com.example.venda_ingressos.dto.SaleDTO
import com.example.venda_ingressos.entities.Client
import com.example.venda_ingressos.entities.Sale
import com.example.venda_ingressos.repository.ClientRepository
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
    fun generateSale(saleDTO: SaleDTO): Sale {
        val totalValue = BigDecimal.valueOf(500)
        val sessionEntity = sessionRepository.findByName(saleDTO.nameSession)

        // todo Melhorar isso mais pra frente
        if (sessionEntity.quantityTickets == 0 || saleDTO.numberOfTickets > sessionEntity.quantityTickets) {
            throw Exception("Não tem mais ingressos suficientes")
        }

        sessionEntity.quantityTickets -= saleDTO.numberOfTickets

        sessionRepository.save(sessionEntity)

        val saleEntity = Sale(
            nameOfSession = sessionEntity.name,
            numberOfTickets = saleDTO.numberOfTickets,
            totalValue = totalValue.multiply(BigDecimal.valueOf(saleDTO.numberOfTickets.toLong())),
            session = sessionEntity
        )

        repository.save(saleEntity)

        clientService.saveClient(saleDTO, saleEntity)

        return saleEntity
    }

    fun getAll(): List<Sale> {
        return repository.findAll()
    }

}