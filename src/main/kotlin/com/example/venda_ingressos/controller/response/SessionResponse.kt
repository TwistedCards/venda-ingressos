package com.example.venda_ingressos.controller.response

import java.time.LocalDateTime
import java.util.*

class SessionResponse(
    val startTime: LocalDateTime,
    val movieName: String,
    val roomName: String
)