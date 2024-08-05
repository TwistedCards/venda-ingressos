package com.example.venda_ingressos.service

import com.example.venda_ingressos.entities.Sale
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.Random

@Service
class SaleService {

    fun generateSale(client: String, numberOfTickets: Int): Sale {
        var operacao: Long = 0
        val totalValue = BigDecimal.valueOf(500)

        return Sale(
            operation = operacao++,
            client = client,
            numberOfTickets = numberOfTickets,
            totalValue = totalValue.multiply(BigDecimal.valueOf(numberOfTickets.toLong()))
        )
    }

}