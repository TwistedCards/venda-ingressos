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
                RoomMovieResponse(
                    id = it.id!!,
                    movieStartTime = it.movieStartTime,
                    movieEndTime = it.movieEndTime
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