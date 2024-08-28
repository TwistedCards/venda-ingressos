package com.example.venda_ingressos.controller.response

import com.example.venda_ingressos.dto.ClientDto

class SaleResponse (
    val nameOfSession: String,
    val clients: MutableList<ClientDto>? = null,
    val numberOfTickets: Int
)