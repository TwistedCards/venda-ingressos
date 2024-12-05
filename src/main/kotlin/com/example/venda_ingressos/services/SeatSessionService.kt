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
    fun save(listSeat: MutableList<SeatEntity>, sessionEntity: SessionEntity) {
        listSeat.forEach {
            repository.save(
                SeatSessionEntity(
                    status = StatusEnum.FREE,
                    seat = it,
                    session = sessionEntity
                )
            )
        }
    }
}