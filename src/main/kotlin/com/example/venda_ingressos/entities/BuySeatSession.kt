package com.example.venda_ingressos.entities

import jakarta.persistence.*
import java.math.BigDecimal
import java.util.*

@Entity
data class BuySeatSession(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,

    @Column(name = "price_paid")
    var pricePaid: BigDecimal, // Preço pago

    var meia: Int,// Valor do ingresso meia

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buy_id", nullable = false)
    var buy: Buy,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id", nullable = false)
    var seat: Seat,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    var session: Session
)