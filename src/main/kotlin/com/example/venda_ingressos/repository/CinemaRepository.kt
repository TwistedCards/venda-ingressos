package com.example.venda_ingressos.repository

import com.example.venda_ingressos.entities.Cinema
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CinemaRepository : JpaRepository<Cinema, UUID> {
}