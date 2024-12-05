package com.example.venda_ingressos.mapper

import com.example.venda_ingressos.controller.model.CinemaModel
import com.example.venda_ingressos.controller.request.CinemaRequest
import com.example.venda_ingressos.controller.response.CinemaResponse
import com.example.venda_ingressos.controller.response.RoomResponse
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