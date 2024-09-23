package com.example.venda_ingressos.dto

import java.math.BigDecimal

class SaleDto(
    val numberOfTickets: Int,
    val totalValue: BigDecimal? = null,
    var status: String? = null
)