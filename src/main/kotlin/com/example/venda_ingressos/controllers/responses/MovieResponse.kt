package com.example.venda_ingressos.controllers.responses

import java.time.LocalDate
import java.util.*

class MovieResponse(
    val id: UUID? = null,
    val name: String,
    val indicativeClassification: String? = null,
    var duration: String,
    val releaseDate: LocalDate,
    val synopsis: String
)