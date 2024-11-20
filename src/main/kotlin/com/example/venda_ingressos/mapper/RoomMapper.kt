package com.example.venda_ingressos.mapper

import com.example.venda_ingressos.controller.request.RoomRequest
import com.example.venda_ingressos.controller.response.RoomResponse
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