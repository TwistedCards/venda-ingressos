package com.example.venda_ingressos.dto

class SaleDto (
    val nameSession: String,
    val numberOfTickets: Int,
    val clients: MutableList<ClientDto>
)