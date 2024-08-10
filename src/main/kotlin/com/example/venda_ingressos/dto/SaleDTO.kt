package com.example.venda_ingressos.dto

class SaleDTO (
    val nameSession: String,
    val numberOfTickets: Int,
    val clients: MutableList<ClientDto>
)