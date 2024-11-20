package com.example.venda_ingressos.mapper

import com.example.venda_ingressos.controller.request.MovieRequest
import com.example.venda_ingressos.controller.response.MovieResponse
import com.example.venda_ingressos.entities.Movie
import org.springframework.stereotype.Component

@Component
class MovieMapper {

    fun entityToResponse(entity: Movie): MovieResponse {
        return MovieResponse(
            title = entity.title,
            originalTitle = entity.originalTitle,
            indicativeClassification = entity.indicativeClassification,
            duration = entity.duration,
            releaseDate = entity.releaseDate,
            synopsis = entity.synopsis
        )
    }

    fun requestToEntity(request: MovieRequest): Movie {
        return Movie(
            title = request.title,
            originalTitle = request.originalTitle,
            indicativeClassification = request.indicativeClassification,
            releaseDate = request.releaseDate,
            synopsis = request.synopsis
        )
    }

}