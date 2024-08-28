package com.example.venda_ingressos.controller.request

import com.example.venda_ingressos.entities.Session
import java.math.BigDecimal

class SaleRequest(
    val nameOfSession: String,
    val clients: MutableList<ClientRequest>? = null,
    val numberOfTickets: Int,
    val totalValue: BigDecimal? = null,
    var status: String? = null,
    val session: Session? = null
)