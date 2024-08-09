package com.example.venda_ingressos.entities

import jakarta.persistence.*
import java.math.BigDecimal
import java.util.*

@Entity
data class Sale(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,

    // todo analisar dps se precisa mesmo dessa variavel
    val nameOfSession: String,

    // inserir a classe Client
//    val client: String,

    val numberOfTickets: Int,
    val totalValue: BigDecimal,
    var status: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id")
    val session: Session // todo retirar o null dps
)