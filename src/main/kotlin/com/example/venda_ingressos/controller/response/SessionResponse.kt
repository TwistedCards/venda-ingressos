package com.example.venda_ingressos.controller.response

import java.time.LocalDateTime
import java.util.*

class SessionResponse(
    val id: UUID? = null,
    val startTime: LocalDateTime,
    val movieName: String,
    val roomName: String
)