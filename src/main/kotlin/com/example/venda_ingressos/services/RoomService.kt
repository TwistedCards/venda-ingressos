package com.example.venda_ingressos.services

import com.example.venda_ingressos.controllers.requests.RoomRequest
import com.example.venda_ingressos.controllers.responses.RoomResponse
import com.example.venda_ingressos.entities.RoomEntity
import com.example.venda_ingressos.exceptions.EntityNotFoundException
import com.example.venda_ingressos.mappers.RoomMapper
import com.example.venda_ingressos.repositorys.CinemaRepository
import com.example.venda_ingressos.repositorys.RoomRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class RoomService(
    private val repository: RoomRepository,
    private val cinemaRepository: CinemaRepository,
    private val mapper: RoomMapper,
    private val seat: SeatService
) {

    fun findById(id: UUID): RoomEntity {
        return repository.findById(id).get()
    }

    @Transactional
    fun save(request: RoomRequest): RoomResponse {
        val entityCinema = cinemaRepository.findById(request.idCinema)
            .orElseThrow { throw EntityNotFoundException("m=save, msg=Cinema with id ${request.idCinema} not found") }

        val roomEntity = repository.save(mapper.requestToEntity(request, entityCinema))

        seat.save(request.totalCapacity, roomEntity)

        return mapper.entityToResponse(roomEntity)
    }

    fun delete(id: UUID) {
        repository.deleteById(id)
    }

}