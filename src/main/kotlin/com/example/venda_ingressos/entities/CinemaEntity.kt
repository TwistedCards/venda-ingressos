package com.example.venda_ingressos.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.util.*

@Entity(name = "Cinema")
class CinemaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,

    @Column(unique = true)
    var phone: String,

    var name: String,

    @JsonIgnore
    @OneToMany(mappedBy = "cinema", fetch = FetchType.LAZY)
    var rooms: MutableList<RoomEntity>? = null
)