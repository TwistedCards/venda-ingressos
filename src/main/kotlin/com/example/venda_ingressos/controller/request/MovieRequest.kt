package com.example.venda_ingressos.controller.request

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate

class MovieRequest(
    val name: String,
    @JsonFormat(pattern = "dd/MM/yyyy")
    val startDate: LocalDate,
    @JsonFormat(pattern = "dd/MM/yyyy")
    val endDate: LocalDate,
    val synopsis: String
)