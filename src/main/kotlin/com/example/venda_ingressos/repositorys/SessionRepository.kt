package com.example.venda_ingressos.repositorys

import com.example.venda_ingressos.entities.SessionEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SessionRepository : JpaRepository<SessionEntity, UUID> {

    fun findByMovieId(id: UUID): List<SessionEntity>? = null

}