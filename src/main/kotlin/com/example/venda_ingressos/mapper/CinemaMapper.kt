package com.example.venda_ingressos.mapper

import com.example.venda_ingressos.controller.request.CinemaRequest
import com.example.venda_ingressos.controller.response.CinemaResponse
import com.example.venda_ingressos.controller.response.RoomResponse
import com.example.venda_ingressos.entities.Cinema
import org.springframework.stereotype.Component

@Component
class CinemaMapper {

    fun entityToResponse(entity: Cinema): CinemaResponse {
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

    fun requestToEntity(request: CinemaRequest): Cinema {
        return Cinema(
            name = request.name,
            phone = request.phone
        )
    }

}