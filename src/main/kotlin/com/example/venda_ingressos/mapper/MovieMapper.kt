package com.example.venda_ingressos.mapper

import com.example.venda_ingressos.controller.request.MovieRequest
import com.example.venda_ingressos.controller.response.MovieResponse
import com.example.venda_ingressos.entities.MovieEntity
import org.springframework.stereotype.Component

@Component
class MovieMapper {

    fun entityToResponse(entity: MovieEntity): MovieResponse {
        return MovieResponse(
            id = entity.id!!,
            name = entity.name,
            indicativeClassification = entity.indicativeClassification,
            duration = entity.duration,
            releaseDate = entity.releaseDate,
            synopsis = entity.synopsis
        )
    }

    fun requestToEntity(request: MovieRequest): MovieEntity {
        return MovieEntity(
            name = request.name,
            indicativeClassification = request.indicativeClassification,
            releaseDate = request.releaseDate,
            synopsis = request.synopsis,
            duration = request.duration
        )
    }

}