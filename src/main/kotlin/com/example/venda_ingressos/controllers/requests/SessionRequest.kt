package com.example.venda_ingressos.controllers.requests

import java.util.*

class SessionRequest(
    val startTime: String,
    val idMovie: UUID,
    val idRoom: UUID
)