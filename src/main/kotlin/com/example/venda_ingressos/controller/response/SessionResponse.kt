package com.example.venda_ingressos.controller.response

class SessionResponse(
    val name: String,
    val datePresentation: String,
    val priceTickets: MutableList<PriceTicketResponse> = mutableListOf()
)