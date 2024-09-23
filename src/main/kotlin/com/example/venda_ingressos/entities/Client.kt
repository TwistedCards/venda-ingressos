package com.example.venda_ingressos.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.util.*

@Entity
data class Client(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,

    val name: String,

    val cpf: String,

    @OneToOne(mappedBy = "client", fetch = FetchType.LAZY)
    var sale: Sale
)