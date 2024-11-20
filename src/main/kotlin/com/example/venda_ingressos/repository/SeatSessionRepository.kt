package com.example.venda_ingressos.repository

import com.example.venda_ingressos.entities.SeatSession
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SeatSessionRepository : JpaRepository<SeatSession, UUID> {

    fun findBySeatIdAndSessionId(seatId: UUID, sessionId: UUID): SeatSession

}