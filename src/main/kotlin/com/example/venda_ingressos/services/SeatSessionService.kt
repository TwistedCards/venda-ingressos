package com.example.venda_ingressos.services

import com.example.venda_ingressos.entities.SeatEntity
import com.example.venda_ingressos.entities.SeatSessionEntity
import com.example.venda_ingressos.entities.SessionEntity
import com.example.venda_ingressos.enums.StatusEnum
import com.example.venda_ingressos.repositorys.SeatSessionRepository
import org.springframework.stereotype.Service

@Service
class SeatSessionService(
    private val repository: SeatSessionRepository
) {

    fun save(seatEntity: SeatEntity? = null, sessionEntity: SessionEntity? = null) {
        repository.saveAndFlush(
            SeatSessionEntity(
                status = StatusEnum.FREE,
                seat = seatEntity,
                session = sessionEntity
            )
        )
    }

}