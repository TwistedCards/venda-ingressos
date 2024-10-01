package com.example.venda_ingressos.controller.response

import java.util.*

class RoomResponse(
    val id: UUID,
    val totalCapacity: Int,
    val roomName: String,
    val roomMovies: MutableList<RoomMovieResponse>? = null
)