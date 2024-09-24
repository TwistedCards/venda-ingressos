package com.example.venda_ingressos.controller.request

import java.util.*

class RoomRequest(
    val totalCapacity: Int,
    val roomName: String,
    val idCinema: UUID
)