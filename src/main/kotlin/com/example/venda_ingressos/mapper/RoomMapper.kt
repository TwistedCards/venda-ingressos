package com.example.venda_ingressos.mapper

import com.example.venda_ingressos.controller.request.CinemaRequest
import com.example.venda_ingressos.controller.request.RoomRequest
import com.example.venda_ingressos.controller.response.CinemaResponse
import com.example.venda_ingressos.controller.response.ClientResponse
import com.example.venda_ingressos.controller.response.RoomMovieResponse
import com.example.venda_ingressos.controller.response.RoomResponse
import com.example.venda_ingressos.entities.Cinema
import com.example.venda_ingressos.entities.Client
import com.example.venda_ingressos.entities.Room
import org.springframework.stereotype.Component

@Component
class RoomMapper {

    fun entityToResponse(entity: Room): RoomResponse {
        return RoomResponse(
            id = entity.id!!,
            totalCapacity = entity.totalCapacity,
            roomName = entity.roomName,
            roomMovies = entity.roomMovies?.map {
                val startTime = "${it.movieStartTime.hour}:${it.movieStartTime.minute}"
                val endTime = "${it.movieEndTime.hour}:${it.movieEndTime.minute}"

                RoomMovieResponse(
                    id = it.id!!,
                    date = it.movieStartTime.toLocalDate(),
                    startTime = startTime,
                    endTime = endTime,
                )
            }?.toMutableList()
        )
    }

    fun requestToEntity(request: RoomRequest, cinemaEntity: Cinema): Room {
        return Room(
            totalCapacity = request.totalCapacity,
            roomName = request.roomName,
            cinema = cinemaEntity
        )
    }

}