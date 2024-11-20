package com.example.venda_ingressos.entities

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity(name = "Session")
class SessionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,

    @Column(name = "start_time")
    var startTime: LocalDateTime,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    var movie: MovieEntity,

    @OneToMany(mappedBy = "session", fetch = FetchType.LAZY)
    var seatSessions: MutableList<SeatSessionEntity>? = null,

    @OneToMany(mappedBy = "session", fetch = FetchType.LAZY)
    var buySeatSessions: MutableList<BuySeatSessionEntity>? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    var room: RoomEntity
)