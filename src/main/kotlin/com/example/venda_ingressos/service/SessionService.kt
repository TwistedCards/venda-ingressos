package com.example.venda_ingressos.service

import com.example.venda_ingressos.controller.model.SessionModel
import com.example.venda_ingressos.controller.request.SessionRequest
import com.example.venda_ingressos.controller.response.SessionResponse
import com.example.venda_ingressos.exceptions.EntityNotFoundException
import com.example.venda_ingressos.exceptions.IllegalArgumentException
import com.example.venda_ingressos.mapper.SessionMapper
import com.example.venda_ingressos.repository.MovieRepository
import com.example.venda_ingressos.repository.RoomRepository
import com.example.venda_ingressos.repository.SessionRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class SessionService(
    private val repository: SessionRepository,
    private val movieRepository: MovieRepository,
    private val roomRepository: RoomRepository,
    private val mapper: SessionMapper
) {

    fun save(request: SessionRequest): SessionResponse {
        val listEntity = repository.findByMovieId(request.idMovie)

        if (!listEntity.isNullOrEmpty()) {
            listEntity.forEach {
                val date = LocalDateTime.parse(request.startTime)
                if (it.startTime == date && it.room.id == request.idRoom) {
                    throw IllegalArgumentException(
                        "m=save, msg=Já existe uma sessão salva na data e hora '$date' " +
                                "na sala '${it.room.roomName}'"
                    )
                }
            }
        }

        val movieEntity = movieRepository.findById(request.idMovie).orElseThrow {
            EntityNotFoundException("O filme com id ${request.idMovie} não foi encontrado.")
        }

        val roomEntity = roomRepository.findById(request.idRoom).orElseThrow {
            EntityNotFoundException("A sala com id ${request.idRoom} não foi encontrada.")
        }

        val entity = mapper.requestToEntity(request, movieEntity, roomEntity)

        return mapper.entityToResponse(repository.save(entity))
    }

    fun getById(id: UUID): SessionModel {
        val entity = repository.findById(id).orElseThrow {
            throw EntityNotFoundException("m=getById, msg=Session com id $id não encontrado")
        }

        return mapper.entityToModel(entity)
    }
}