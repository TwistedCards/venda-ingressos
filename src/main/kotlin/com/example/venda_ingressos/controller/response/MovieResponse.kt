package com.example.venda_ingressos.controller.response

import java.time.LocalDate
import java.time.LocalDateTime

class MovieResponse(
    val title: String,
    val originalTitle: String,
    val indicativeClassification: String? = null,
    var duration: String,
    val releaseDate: LocalDate,
    val synopsis: String
)