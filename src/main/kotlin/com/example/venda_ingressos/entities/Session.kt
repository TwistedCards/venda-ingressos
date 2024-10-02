package com.example.venda_ingressos.entities

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

class Session(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,

    var startTime: LocalDateTime,

    var subtitled: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    var movie: Movie
)