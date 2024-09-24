package com.example.venda_ingressos.controller.request

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import java.util.*

class RoomMovieRequest(
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    val movieStartTime: LocalDateTime,

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    val movieEndTime: LocalDateTime,

    val idRoom: UUID,

    val idMovie: UUID
)