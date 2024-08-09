package com.example.venda_ingressos.service

import com.example.venda_ingressos.dto.SaleDTO
import com.example.venda_ingressos.entities.Sale
import com.example.venda_ingressos.repository.SaleRepository
import com.example.venda_ingressos.repository.SessionRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.Random

@Service
class SaleService(
    val repository: SaleRepository,
    val sessionRepository: SessionRepository
) {

    // todo inserir o transactional dps
    fun generateSale(saleDTO: SaleDTO): Sale {
        val totalValue = BigDecimal.valueOf(500)
        val sessionEntity = sessionRepository.findByName(saleDTO.nameSession)

        // todo Melhorar isso mais pra frente
        if (sessionEntity.quantityTickets == 0) {
            throw Exception("Ingressos esgotados")
        }

        sessionEntity.quantityTickets -= saleDTO.numberOfTickets

        sessionRepository.save(sessionEntity)

        val saleEntity = Sale(
            nameOfSession = sessionEntity.name,
//            client = "Teste cliente",
            numberOfTickets = saleDTO.numberOfTickets,
            totalValue = totalValue.multiply(BigDecimal.valueOf(saleDTO.numberOfTickets.toLong())),
            session = sessionEntity
        )

        return repository.save(saleEntity)
    }

    fun getAll(): List<Sale> {
        return repository.findAll()
    }

}