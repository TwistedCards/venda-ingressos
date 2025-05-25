package com.example.venda_ingressos.mappers

import com.example.venda_ingressos.controllers.responses.SeatResponse
import com.example.venda_ingressos.entities.SeatEntity
import org.springframework.stereotype.Component

@Component
class SeatMapper {

    fun entityToResponse(listEntity: MutableList<SeatEntity>): List<SeatResponse> {
        return listEntity.map { entity ->
            SeatResponse(
                codSeat = entity.codSeat,
                category = entity.category.name,
                status = entity.seatSessions?.first { seatSession -> seatSession.seat?.id == entity.id }!!.status.name,
                roomName = entity.room.roomName
            )
        }
    }

}