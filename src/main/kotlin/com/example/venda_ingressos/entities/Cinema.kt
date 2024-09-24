package com.example.venda_ingressos.entities

import jakarta.persistence.*
import java.util.*

@Entity
class Cinema(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,

    var phone: String,

    var name: String,

    @OneToMany(mappedBy = "cinema", fetch = FetchType.LAZY)
    var rooms: MutableList<Room>? = null
)