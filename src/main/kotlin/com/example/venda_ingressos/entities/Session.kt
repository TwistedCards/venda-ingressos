package com.example.venda_ingressos.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

@Entity
data class Session(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,
    val name: String,
    var quantityTickets: Int,
    val datePresentation: LocalDate
)