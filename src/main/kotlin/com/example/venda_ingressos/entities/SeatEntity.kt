package com.example.venda_ingressos.entities

import com.example.venda_ingressos.enums.CategoryEnum
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.util.*

@Entity(name = "Seat")
class SeatEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,

    @Column(name = "cod_seat")
    var codSeat: String,

    @Enumerated(EnumType.STRING)
    var category: CategoryEnum,

    @JsonIgnore
    @OneToMany(mappedBy = "seat", fetch = FetchType.LAZY)
    var seatSessions: MutableList<SeatSessionEntity>? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    var room: RoomEntity
)