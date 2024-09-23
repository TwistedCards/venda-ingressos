package com.example.venda_ingressos.entities

import jakarta.persistence.*
import java.util.*

@Entity
data class Client(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,

    val name: String,

    @Column(unique = true)
    val cpf: String
)