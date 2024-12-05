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
    private val mapper: SessionMapper,
    private val seatService: SeatService,
    private val seatSessionService: SeatSessionService
) {

    fun save(request: SessionRequest): SessionResponse {
        val listEntity = repository.findByMovieId(request.idMovie)

        if (!listEntity.isNullOrEmpty()) {
            listEntity.forEach {
                val date = LocalDateTime.parse(request.startTime)

                if (it.startTime == date && it.room.id == request.idRoom) {
                    throw IllegalArgumentException(
                        "m=save, msg=There is already a session saved at the date and time '$date' " +
                                "in the room '${it.room.roomName}'"
                    )
                }
            }
        }

        val movieEntity = movieRepository.findById(request.idMovie).orElseThrow {
            EntityNotFoundException("m=save, msg=the movie with id ${request.idMovie} is not found.")
        }

        val roomEntity = roomRepository.findById(request.idRoom).orElseThrow {
            EntityNotFoundException("m=save, msg=the movie with id ${request.idRoom} is not found.")
        }

        val entity = mapper.requestToEntity(request, movieEntity, roomEntity)
        val listSeat = seatService.findSeatByRoomId(request.idRoom)
        val response = mapper.entityToResponse(repository.save(entity))

        seatSessionService.save(listSeat, entity)

        return response
    }

    fun getById(id: UUID): SessionModel {
        val entity = repository.findById(id).orElseThrow {
            throw EntityNotFoundException("m=getById, msg=Session with id $id is not found.")
        }

        return mapper.entityToModel(entity)
    }

}