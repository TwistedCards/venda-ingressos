package com.example.venda_ingressos.controller.response

import java.time.LocalDate
import java.util.*

class MovieResponse(
    val id: UUID,
    val name: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val synopsis: String
)