package com.example.venda_ingressos.controller.request

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import java.util.*

class SessionRequest(
    val startTime: String,
    val idMovie: UUID,
    val idRoom: UUID
)