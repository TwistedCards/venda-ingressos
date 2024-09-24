package com.example.venda_ingressos.mapper

import com.example.venda_ingressos.controller.request.MovieRequest
import com.example.venda_ingressos.controller.response.MovieResponse
import com.example.venda_ingressos.controller.response.RoomMovieResponse
import com.example.venda_ingressos.entities.Movie
import org.springframework.stereotype.Component

@Component
class MovieMapper {

    fun entityToResponse(entity: Movie): MovieResponse {
        return MovieResponse(
            id = entity.id!!,
            name = entity.name,
            startDate = entity.startDate,
            endDate = entity.endDate,
            synopsis = entity.synopsis,
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

    fun requestToEntity(request: MovieRequest): Movie {
        return Movie(
            name = request.name,
            startDate = request.startDate,
            endDate = request.endDate,
            synopsis = request.synopsis
        )
    }

}