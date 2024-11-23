package com.example.venda_ingressos.controller.model

import com.example.venda_ingressos.entities.MovieEntity
import com.example.venda_ingressos.entities.RoomEntity
import java.time.LocalDateTime
import java.util.*

class SessionModel(
    val id: UUID? = null,
    val startTime: LocalDateTime,
    val movie: MovieEntity? = null,
    val room: RoomEntity? = null
)