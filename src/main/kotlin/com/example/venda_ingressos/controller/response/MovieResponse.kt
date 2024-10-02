package com.example.venda_ingressos.controller.response

import java.time.LocalDate
import java.time.LocalDateTime

class MovieResponse(
    val title: String,
    val originalTitle: String,
    var imgPoster: String? = null,
    var trailer: String? = null,
    val indicativeClassification: String? = null,
    var duration: LocalDateTime? = null,
    val releaseDate: LocalDate,
    val synopsis: String
)