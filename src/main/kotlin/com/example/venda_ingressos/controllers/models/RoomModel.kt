package com.example.venda_ingressos.controllers.models

import java.util.*

class RoomModel(
    val id: UUID,
    val totalCapacity: Int,
    val roomName: String,
    val cinema: CinemaModel
)