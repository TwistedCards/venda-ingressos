package com.example.venda_ingressos.controller.request

import java.time.LocalDate

class MovieRequest(
    val name: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val synopsis: String
)