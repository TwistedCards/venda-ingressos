package com.example.venda_ingressos.mappers

import com.example.venda_ingressos.controllers.models.CinemaModel
import com.example.venda_ingressos.controllers.requests.CinemaRequest
import com.example.venda_ingressos.controllers.responses.CinemaResponse
import com.example.venda_ingressos.controllers.responses.RoomResponse
import com.example.venda_ingressos.entities.CinemaEntity
import org.springframework.stereotype.Component

@Component
class CinemaMapper {

    fun entityToResponse(entity: CinemaEntity): CinemaResponse {
        return CinemaResponse(
            id = entity.id!!,
            name = entity.name,
            phone = entity.phone,
            rooms = entity.rooms?.map {
                RoomResponse(
                    id = it.id!!,
                    totalCapacity = it.totalCapacity,
                    roomName = it.roomName
                )
            }?.toMutableList()
        )
    }

    fun requestToEntity(request: CinemaRequest): CinemaEntity {
        return CinemaEntity(
            name = request.name,
            phone = request.phone
        )
    }

    fun entityToListModel(entity: MutableList<CinemaEntity>): List<CinemaModel> {
        return entity.map { CinemaModel(id = it.id, name = it.name, phone = it.phone) }
    }

}