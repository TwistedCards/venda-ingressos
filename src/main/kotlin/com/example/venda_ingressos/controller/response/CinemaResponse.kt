package com.example.venda_ingressos.controller.response

import com.example.venda_ingressos.entities.Room
import java.util.*

class CinemaResponse(
    val id: UUID,
    val phone: String,
    val name: String,
    val room: RoomResponse? = null
)