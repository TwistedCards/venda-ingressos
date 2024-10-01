package com.example.venda_ingressos.controller.response

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class RoomMovieResponse(
    val id: UUID,
    val date: LocalDate,
    val startTime: String,
    val endTime: String
)