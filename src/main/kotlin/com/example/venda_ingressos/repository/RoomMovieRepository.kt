package com.example.venda_ingressos.repository

import com.example.venda_ingressos.entities.RoomMovie
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RoomMovieRepository : JpaRepository<RoomMovie, UUID> {
}