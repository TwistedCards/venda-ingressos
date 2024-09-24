package com.example.venda_ingressos.controller.response

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import java.util.*

class RoomMovieResponse(
    val id: UUID,
    val movieStartTime: LocalDateTime,
    val movieEndTime: LocalDateTime
)