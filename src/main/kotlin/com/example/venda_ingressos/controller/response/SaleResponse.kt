package com.example.venda_ingressos.controller.response

import com.example.venda_ingressos.dto.ClientDto

class SaleResponse(
    val nameOfSession: String,
    val clients: ClientDto? = null,
    val numberOfTickets: Int
)