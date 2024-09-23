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

    @JsonIgnore
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    val client: Client? = null,

    val numberOfTickets: Int,

    val totalValue: BigDecimal,

    var status: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id")
    val session: Session? = null
)