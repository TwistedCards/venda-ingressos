package com.example.venda_ingressos.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.util.*

@Entity
data class Room(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,

    var totalCapacity: Int,

    var roomName: String,

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_id", nullable = false)
    var cinema: Cinema,

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    var roomMovies: MutableList<RoomMovie> = mutableListOf()
)