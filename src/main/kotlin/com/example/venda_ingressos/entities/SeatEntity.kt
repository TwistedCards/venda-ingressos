package com.example.venda_ingressos.entities

import jakarta.persistence.*
import java.util.*

@Entity(name = "Seat")
data class SeatEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,

    @Column(name = "cod_seat")
    var codSeat: String,

    @Enumerated(EnumType.STRING)
    var category: CategoryEnum,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    var movie: MovieEntity,

    @OneToMany(mappedBy = "seat", fetch = FetchType.LAZY)
    var seatSessions: MutableList<SeatSessionEntity>? = null,

    @OneToMany(mappedBy = "seat", fetch = FetchType.LAZY)
    var buySeatSessions: MutableList<BuySeatSessionEntity>? = null
)