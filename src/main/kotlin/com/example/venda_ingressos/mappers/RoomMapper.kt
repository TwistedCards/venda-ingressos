package com.example.venda_ingressos.mappers

import com.example.venda_ingressos.controllers.models.CinemaModel
import com.example.venda_ingressos.controllers.models.RoomModel
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

    fun listEntityToListResponse(listRoomEntity: List<RoomEntity>): List<RoomModel> {
        return listRoomEntity.map {
            RoomModel(
                id = it.id!!,
                totalCapacity = it.totalCapacity,
                roomName = it.roomName,
                cinema = CinemaModel(
                    id = it.cinema.id,
                    phone = it.cinema.phone,
                    name = it.cinema.name
                )
            )
        }
    }

}