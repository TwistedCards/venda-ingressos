package com.example.venda_ingressos.controller.request

import java.math.BigDecimal

class SessionRequest (
    val name: String,
    val quantityTickets: Int,
    val datePresentation: String,
    val valueOfTickets: BigDecimal? = null
)