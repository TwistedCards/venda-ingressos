package com.example.venda_ingressos.entities

import jakarta.persistence.*
import java.util.*

@Entity
data class Seat(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,

    @Column(name = "cod_seat")
    var codSeat: String,

    @Enumerated(EnumType.STRING)
    var category: CategoryEnum,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    var movie: Movie,

    @OneToMany(mappedBy = "seat", fetch = FetchType.LAZY)
    var seatSessions: MutableList<SeatSession>? = null,

    @OneToMany(mappedBy = "seat", fetch = FetchType.LAZY)
    var buySeatSessions: MutableList<BuySeatSession>? = null
)