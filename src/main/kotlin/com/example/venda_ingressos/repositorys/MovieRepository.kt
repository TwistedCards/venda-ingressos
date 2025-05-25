package com.example.venda_ingressos.repositorys

import com.example.venda_ingressos.entities.MovieEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MovieRepository : JpaRepository<MovieEntity, UUID> {
}