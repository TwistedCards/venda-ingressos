package com.example.venda_ingressos.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.util.*

@Entity(name = "Seat_Session")
class SeatSessionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,

    @Enumerated(EnumType.STRING)
    var status: StatusEnum,

//    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id", nullable = false)
    var seat: SeatEntity,

//    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    var session: SessionEntity,

    @OneToMany(mappedBy = "seatSession", fetch = FetchType.LAZY)
    var buySeatSessions: MutableList<BuySeatSessionEntity>? = null
)