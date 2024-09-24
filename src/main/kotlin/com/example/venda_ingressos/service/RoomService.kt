package com.example.venda_ingressos.service

import com.example.venda_ingressos.controller.request.RoomRequest
import com.example.venda_ingressos.controller.request.paged.PagedRequest
import com.example.venda_ingressos.controller.response.RoomResponse
import com.example.venda_ingressos.entities.Room
import com.example.venda_ingressos.mapper.RoomMapper
import com.example.venda_ingressos.repository.CinemaRepository
import com.example.venda_ingressos.repository.RoomRepository
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.util.*

@Service
class RoomService(
    private val repository: RoomRepository,
    private val cinemaRepository: CinemaRepository,
    private val mapper: RoomMapper
) {

    fun findById(id: UUID): Room {
        return repository.findById(id).get()
    }

    fun save(request: RoomRequest): RoomResponse {
        val entityCinema = cinemaRepository.findById(request.idCinema).get()
        val roomEntity = repository.save(mapper.requestToEntity(request, entityCinema))

        return mapper.entityToResponse(roomEntity)
    }

    fun findAll(pagedRequest: PagedRequest): Page<RoomResponse> {
        return repository.findAll(pagedRequest.pageable()).map { mapper.entityToResponse(it) }
    }

    fun delete(id: UUID) {
        repository.deleteById(id)
    }

}