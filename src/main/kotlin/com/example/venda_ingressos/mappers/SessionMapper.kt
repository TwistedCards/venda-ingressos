package com.example.venda_ingressos.mappers

import com.example.venda_ingressos.controllers.models.SessionModel
import com.example.venda_ingressos.controllers.requests.SessionRequest
import com.example.venda_ingressos.controllers.responses.SessionResponse
import com.example.venda_ingressos.entities.MovieEntity
import com.example.venda_ingressos.entities.RoomEntity
import com.example.venda_ingressos.entities.SessionEntity
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class SessionMapper {

    fun entityToResponse(entity: SessionEntity): SessionResponse {
        return SessionResponse(
            id = entity.id,
            startTime = entity.startTime,
            roomName = entity.room.roomName,
            movieName = entity.movie.name
        )
    }

    fun entityToModel(entity: SessionEntity): SessionModel {
        return SessionModel(
            id = entity.id,
            startTime = entity.startTime,
            room = entity.room,
            movie = entity.movie
        )
    }

    fun requestToEntity(request: SessionRequest, movieEntity: MovieEntity, roomEntity: RoomEntity): SessionEntity {
        val date = LocalDateTime.parse(request.startTime)

        return SessionEntity(
            startTime = date,
            movie = movieEntity,
            room = roomEntity
        )
    }

}