package com.example.venda_ingressos.controller.response

import java.time.LocalDateTime
import java.util.*

class RoomMovieResponse(
    val id: UUID,
    val movieTime: LocalDateTime
)