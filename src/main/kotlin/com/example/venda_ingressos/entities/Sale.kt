package com.example.venda_ingressos.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.math.BigDecimal
import java.util.*

@Entity
data class Sale(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,

    // todo analisar dps se precisa mesmo dessa variavel
    val nameOfSession: String,

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sale", cascade = [CascadeType.ALL])
    val clients: MutableList<Client> = mutableListOf(),

    val numberOfTickets: Int,
    val totalValue: BigDecimal,
    var status: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id")
    val session: Session
)