package com.example.venda_ingressos.controllers.requests

import java.util.*

class RoomRequest(
    val totalCapacity: Int,
    val roomName: String,
    val idCinema: UUID
)