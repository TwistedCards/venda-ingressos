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

    @Column(name="original_title")
    var originalTitle: String,

    @Column(name="img_poster")
    var imgPoster: String? = null,

    var trailer: String? = null,

    @Column(name="indicative_classification")
    var indicativeClassification: String? = null,

    var duration: LocalDateTime? = null,

    @Column(name = "release_date")
    var releaseDate: LocalDate,

    @Lob
    var synopsis: String,

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    var sessions: MutableList<Session>? = null,

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    var seats: MutableList<Seat>? = null
)