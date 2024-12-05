package com.example.venda_ingressos.services

import com.example.venda_ingressos.controllers.requests.MovieRequest
import com.example.venda_ingressos.controllers.requests.paged.PagedRequest
import com.example.venda_ingressos.controllers.responses.MovieResponse
import com.example.venda_ingressos.entities.MovieEntity
import com.example.venda_ingressos.exceptions.DataIntegrityViolationException as DataIntegrityViolationExceptionLocal
import com.example.venda_ingressos.exceptions.EntityNotFoundException
import com.example.venda_ingressos.mappers.MovieMapper
import com.example.venda_ingressos.repositorys.MovieRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.util.*

@Service
class MovieService(
    private val repository: MovieRepository,
    private val mapper: MovieMapper
) {

    fun findById(id: UUID): MovieEntity {
        return repository.findById(id).orElseThrow { EntityNotFoundException("O filme com id $id não foi encontrado") }
    }

    fun save(request: MovieRequest): MovieResponse {
        try {
            val entity = repository.save(mapper.requestToEntity(request))

            return mapper.entityToResponse(entity)
        } catch (e: DataIntegrityViolationException){
            throw DataIntegrityViolationExceptionLocal(
                "m:save, msg:O nome '${request.name}' já existe na base."
            )
        }

    }

    fun findAll(pagedRequest: PagedRequest): Page<MovieResponse> {
        return repository.findAll(pagedRequest.pageable()).map { mapper.entityToResponse(it) }
    }

    fun delete(id: UUID){
        repository.deleteById(id)
    }
}