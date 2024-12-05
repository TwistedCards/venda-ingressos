package com.example.venda_ingressos.mappers

import com.example.venda_ingressos.controllers.requests.RoomRequest
import com.example.venda_ingressos.controllers.responses.RoomResponse
import com.example.venda_ingressos.entities.CinemaEntity
import com.example.venda_ingressos.entities.RoomEntity
import org.springframework.stereotype.Component

@Component
class RoomMapper {

    fun entityToResponse(entity: RoomEntity): RoomResponse {
        return RoomResponse(
            id = entity.id!!,
            totalCapacity = entity.totalCapacity,
            roomName = entity.roomName
        )
    }

    fun requestToEntity(request: RoomRequest, cinemaEntity: CinemaEntity): RoomEntity {
        return RoomEntity(
            totalCapacity = request.totalCapacity,
            roomName = request.roomName,
            cinema = cinemaEntity
        )
    }

}