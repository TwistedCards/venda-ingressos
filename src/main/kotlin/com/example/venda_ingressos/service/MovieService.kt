package com.example.venda_ingressos.service

import com.example.venda_ingressos.controller.request.MovieRequest
import com.example.venda_ingressos.controller.request.paged.PagedRequest
import com.example.venda_ingressos.controller.response.MovieResponse
import com.example.venda_ingressos.entities.Movie
import com.example.venda_ingressos.exceptions.EntityNotFoundException
import com.example.venda_ingressos.mapper.MovieMapper
import com.example.venda_ingressos.repository.MovieRepository
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.util.*

@Service
class MovieService(
    private val repository: MovieRepository,
    private val mapper: MovieMapper
) {

    fun findById(id: UUID): Movie {
        return repository.findById(id).orElseThrow { EntityNotFoundException("O filme com id $id não foi encontrado") }
    }

    fun save(request: MovieRequest): MovieResponse {
        val entity = repository.save(mapper.requestToEntity(request))

        return mapper.entityToResponse(entity)
    }

    fun findAll(pagedRequest: PagedRequest): Page<MovieResponse> {
        return repository.findAll(pagedRequest.pageable()).map { mapper.entityToResponse(it) }
    }

    fun delete(id: UUID){
        repository.deleteById(id)
    }
}