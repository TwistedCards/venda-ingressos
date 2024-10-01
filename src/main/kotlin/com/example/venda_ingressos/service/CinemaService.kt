package com.example.venda_ingressos.service

import com.example.venda_ingressos.controller.request.CinemaRequest
import com.example.venda_ingressos.controller.request.paged.PagedRequest
import com.example.venda_ingressos.controller.response.CinemaResponse
import com.example.venda_ingressos.entities.Cinema
import com.example.venda_ingressos.mapper.CinemaMapper
import com.example.venda_ingressos.repository.CinemaRepository
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.util.*

@Service
class CinemaService(
    private val repository: CinemaRepository,
    private val mapper: CinemaMapper
) {

    fun findById(id: UUID): Cinema {
        return repository.findById(id).get()
    }

    fun findAll(pagedRequest: PagedRequest): Page<CinemaResponse> {
        return repository.findAll(pagedRequest.pageable()).map { mapper.entityToResponse(it) }
    }

    fun save(request: CinemaRequest): CinemaResponse {
        val entitySave = repository.save(mapper.requestToEntity(request))
        return mapper.entityToResponse(entitySave)
    }

    fun delete(id: UUID){
        repository.deleteById(id)
    }
}