package com.example.venda_ingressos.repository

import com.example.venda_ingressos.entities.CinemaEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CinemaRepository : JpaRepository<CinemaEntity, UUID> {
}