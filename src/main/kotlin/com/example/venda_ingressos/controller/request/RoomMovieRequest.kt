package com.example.venda_ingressos.controller.request

import java.time.LocalDateTime
import java.util.*

class RoomMovieRequest(
    val movieTime: LocalDateTime,
    val idRoom: UUID,
    val idMovie: UUID
)