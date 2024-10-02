package com.example.venda_ingressos.entities

import jakarta.persistence.*

class Seat(
    var codSeat: String,

    @Enumerated(EnumType.STRING)
    var category: CategoryEnum,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    var movie: Movie
)