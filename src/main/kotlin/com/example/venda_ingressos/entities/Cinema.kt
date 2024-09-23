package com.example.venda_ingressos.entities

import jakarta.persistence.*

@Entity
class Cinema(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    var phone: String,

    var name: String,

    @OneToMany(mappedBy = "cinema", fetch = FetchType.LAZY)
    var rooms: MutableList<Room> = mutableListOf()
)