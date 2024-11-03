package com.example.venda_ingressos.entities

import jakarta.persistence.*
import java.util.*

@Entity
data class Buy(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,

    @Column(name = "cod_buy")
    var codBuy: Int, // Codigo da compra

    @OneToMany(mappedBy = "buy", fetch = FetchType.LAZY)
    var buySeatSessions: MutableList<BuySeatSession>? = null
)