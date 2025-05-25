package com.example.venda_ingressos.entities

import com.example.venda_ingressos.enums.StatusEnum
import jakarta.persistence.*
import java.util.*

@Entity(name = "Seat_Session")
class SeatSessionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,

    @Enumerated(EnumType.STRING)
    var status: StatusEnum,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id", nullable = true)
    var seat: SeatEntity? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = true)
    var session: SessionEntity? = null,

    @OneToMany(mappedBy = "seatSession", fetch = FetchType.LAZY)
    var buySeatSessions: MutableList<BuySeatSessionEntity>? = null
)