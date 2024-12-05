package com.example.venda_ingressos.controllers.responses

import java.util.*

class RoomResponse(
    val id: UUID,
    val totalCapacity: Int,
    val roomName: String
//    val roomMovies: MutableList<RoomMovieResponse>? = null
)