package com.example.venda_ingressos.entities

import java.math.BigDecimal

class Sale(
    val operation: Long,
    val client: String,
    val numberOfTickets: Int,
    val totalValue: BigDecimal,
    var status: String? = null
)