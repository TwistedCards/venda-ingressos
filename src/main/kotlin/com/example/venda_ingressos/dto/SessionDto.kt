package com.example.venda_ingressos.dto

import java.time.LocalDate

data class SessionDto (
    val name: String,
    val quantityTickets: Int,
    val datePresentation: String
)