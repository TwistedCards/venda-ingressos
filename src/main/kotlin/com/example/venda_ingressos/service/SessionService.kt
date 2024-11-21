package com.example.venda_ingressos.service

import com.example.venda_ingressos.controller.request.SessionRequest
import com.example.venda_ingressos.controller.response.SeatResponse
import com.example.venda_ingressos.controller.response.SessionResponse
import com.example.venda_ingressos.entities.StatusEnum
import com.example.venda_ingressos.exceptions.EntityNotFoundException
import com.example.venda_ingressos.exceptions.IllegalArgumentException
import com.example.venda_ingressos.mapper.SessionMapper
import com.example.venda_ingressos.repository.*
import org.springframework.stereotype.Service
import java.util.*

@Service
class SessionService(
    private val repository: SessionRepository,
    private val movieRepository: MovieRepository,
    private val roomRepository: RoomRepository,
    private val mapper: SessionMapper
) {

    fun save(request: SessionRequest): SessionResponse {
        val movieEntity = movieRepository.findById(request.idMovie).orElseThrow {
            EntityNotFoundException("O filme com id ${request.idMovie} não foi encontrado.")
        }

        val roomEntity = roomRepository.findById(request.idRoom).orElseThrow {
            EntityNotFoundException("A sala com id ${request.idRoom} não foi encontrada.")
        }

        val entity = mapper.requestToEntity(request, movieEntity, roomEntity)

        return mapper.entityToResponse(repository.save(entity))
    }
}