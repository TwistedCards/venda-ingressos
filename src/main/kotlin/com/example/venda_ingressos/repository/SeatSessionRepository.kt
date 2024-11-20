package com.example.venda_ingressos.repository

import com.example.venda_ingressos.entities.SeatSessionEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SeatSessionRepository : JpaRepository<SeatSessionEntity, UUID> {

    fun findBySeatIdAndSessionId(seatId: UUID, sessionId: UUID): SeatSessionEntity

}