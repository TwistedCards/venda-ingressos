package com.example.venda_ingressos.dto

import com.example.venda_ingressos.entities.Session
import java.math.BigDecimal

class SaleDto(
    val nameOfSession: String,
    val numberOfTickets: Int,
    val totalValue: BigDecimal? = null,
    var status: String? = null,
//    val session: Session? = null
)