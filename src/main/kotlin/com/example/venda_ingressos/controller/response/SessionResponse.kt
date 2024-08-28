package com.example.venda_ingressos.controller.response

import java.math.BigDecimal

class SessionResponse(
    val name: String,
    val quantityTickets: Int,
    val datePresentation: String,
    val valueOfTickets: BigDecimal? = null
)