package com.example.venda_ingressos.entities

import jakarta.persistence.*
import java.util.*

@Entity
data class Client(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,

    var name: String,

    @Column(unique = true)
    var cpf: String
)