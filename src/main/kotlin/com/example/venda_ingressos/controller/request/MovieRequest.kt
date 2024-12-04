package com.example.venda_ingressos.controller.request

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate

class MovieRequest(
    val name: String,
    val indicativeClassification: String,
    @JsonFormat(pattern = "dd/MM/yyyy")
    val releaseDate: LocalDate,
    val synopsis: String,
    val duration: String
)