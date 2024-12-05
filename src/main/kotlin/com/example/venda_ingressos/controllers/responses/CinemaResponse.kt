package com.example.venda_ingressos.controllers.responses

import java.util.*

class CinemaResponse(
    val id: UUID,
    val phone: String,
    val name: String,
    val rooms: MutableList<RoomResponse>? = null
)