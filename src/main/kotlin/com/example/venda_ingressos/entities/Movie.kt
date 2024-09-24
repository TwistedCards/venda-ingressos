package com.example.venda_ingressos.entities

import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity
data class Movie(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,

    @Column(unique = true)
    var name: String,

    var startDate: LocalDate,

    var endDate: LocalDate,

    @Lob
    var synopsis: String,

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    var roomMovies: MutableList<RoomMovie>? = null
)