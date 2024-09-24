package com.example.venda_ingressos.mapper

import com.example.venda_ingressos.controller.request.RoomMovieRequest
import com.example.venda_ingressos.controller.response.RoomMovieResponse
import com.example.venda_ingressos.entities.Movie
import com.example.venda_ingressos.entities.Room
import com.example.venda_ingressos.entities.RoomMovie
import org.springframework.stereotype.Component

@Component
class RoomMovieMapper {

    fun entityToResponse(entity: RoomMovie): RoomMovieResponse {
        return RoomMovieResponse(
            id = entity.id!!,
            movieTime = entity.movieTime
        )
    }

    fun requestToEntity(request: RoomMovieRequest, movieEntity: Movie, roomEntity: Room): RoomMovie {
        return RoomMovie(
            movieTime = request.movieTime,
            movie = movieEntity,
            room = roomEntity
        )
    }

}