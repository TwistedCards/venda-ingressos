package com.example.venda_ingressos.entities

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Entity
data class Movie(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,

    var title: String,

    var originalTitle: String,

    var imgPoster: String? = null,

    var trailer: String? = null,

    var indicativeClassification: String? = null,

    var duration: LocalDateTime? = null,

    var releaseDate: LocalDate,

    @Lob
    var synopsis: String,

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    var sessions: MutableList<Session>? = null,

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    var seats: MutableList<Seat>? = null
)