package com.example.venda_ingressos.entities

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
class Session(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,

    @Column(name = "start_time")
    var startTime: LocalDateTime,

    // TODO tirar duvida sobre esse cara, no site ele ta como tinyint
//    var subtitled: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    var movie: Movie,

    @OneToMany(mappedBy = "session", fetch = FetchType.LAZY)
    var seatSessions: MutableList<SeatSession>? = null,

    @OneToMany(mappedBy = "session", fetch = FetchType.LAZY)
    var buySeatSessions: MutableList<BuySeatSession>? = null
)