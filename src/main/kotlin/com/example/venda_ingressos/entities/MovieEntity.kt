package com.example.venda_ingressos.entities

import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity(name = "Movie")
data class MovieEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,

    @Column(unique = true)
    var title: String,

    @Column(name="original_title")
    var originalTitle: String,

    @Column(name="indicative_classification")
    var indicativeClassification: String? = null,

    var duration: String,

    @Column(name = "release_date")
    var releaseDate: LocalDate,

    @Lob
    var synopsis: String,

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    var sessions: MutableList<SessionEntity>? = null,

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    var seats: MutableList<SeatEntity>? = null
)