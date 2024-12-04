package com.example.venda_ingressos.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.util.*

@Entity(name = "Room")
class RoomEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,

    @Column(name = "total_capacity")
    var totalCapacity: Int,

    @Column(name = "room_name")
    var roomName: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_id", nullable = false)
    var cinema: CinemaEntity,

    @JsonIgnore
    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    var sessions: MutableList<SessionEntity>? = null

//    @JsonIgnore
//    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
//    var seats: MutableList<SeatEntity>? = null
)