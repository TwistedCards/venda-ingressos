package com.example.venda_ingressos.dto

import java.math.BigDecimal

data class SessionDto(
    val name: String,
    val quantityTickets: Int,
    val datePresentation: String,
    val valueOfTickets: BigDecimal? = null
)