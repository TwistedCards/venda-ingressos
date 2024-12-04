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
    var name: String,

    @Column(name = "indicative_classification")
    var indicativeClassification: String? = null,

    var duration: String,

    @Column(name = "release_date")
    var releaseDate: LocalDate,

    @Lob
    var synopsis: String,

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    var sessions: MutableList<SessionEntity>? = null
)