package com.example.venda_ingressos.controller.response

import java.math.BigDecimal

class PriceTicketResponse(
    val price: BigDecimal,
    val type: String,
    val quantityTickets: Int
)