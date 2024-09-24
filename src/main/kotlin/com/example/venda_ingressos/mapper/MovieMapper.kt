package com.example.venda_ingressos.mapper

import com.example.venda_ingressos.controller.request.CinemaRequest
import com.example.venda_ingressos.controller.request.MovieRequest
import com.example.venda_ingressos.controller.response.CinemaResponse
import com.example.venda_ingressos.controller.response.ClientResponse
import com.example.venda_ingressos.controller.response.MovieResponse
import com.example.venda_ingressos.entities.Cinema
import com.example.venda_ingressos.entities.Client
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
            synopsis = entity.synopsis
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