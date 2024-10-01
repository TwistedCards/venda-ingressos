package com.example.venda_ingressos.repository

import com.example.venda_ingressos.entities.Movie
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MovieRepository : JpaRepository<Movie, UUID> {
}