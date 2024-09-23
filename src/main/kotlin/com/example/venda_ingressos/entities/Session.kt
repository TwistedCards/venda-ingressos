package com.example.venda_ingressos.entities

import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity
data class Session(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,

    @Column(unique = true)
    val name: String,

    val datePresentation: LocalDate,

    @OneToMany(mappedBy = "session", fetch = FetchType.LAZY)
    var priceTickets: MutableList<PriceTicket> = mutableListOf()
)