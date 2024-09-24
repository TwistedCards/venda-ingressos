package com.example.venda_ingressos.service

import com.example.venda_ingressos.controller.request.CinemaRequest
import com.example.venda_ingressos.controller.request.RoomMovieRequest
import com.example.venda_ingressos.controller.request.paged.PagedRequest
import com.example.venda_ingressos.controller.response.CinemaResponse
import com.example.venda_ingressos.controller.response.RoomMovieResponse
import com.example.venda_ingressos.entities.RoomMovie
import com.example.venda_ingressos.mapper.RoomMovieMapper
import com.example.venda_ingressos.repository.MovieRepository
import com.example.venda_ingressos.repository.RoomMovieRepository
import com.example.venda_ingressos.repository.RoomRepository
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.util.*

@Service
class RoomMovieService(
    private val repository: RoomMovieRepository,
    private val roomRepository: RoomRepository,
    private val movieRepository: MovieRepository,
    private val mapper: RoomMovieMapper
) {

    fun findAll(pagedRequest: PagedRequest): Page<RoomMovieResponse> {
        return repository.findAll(pagedRequest.pageable()).map { mapper.entityToResponse(it) }
    }

    fun save(request: RoomMovieRequest): RoomMovieResponse {
        val movie = movieRepository.findById(request.idMovie).get()
        val room = roomRepository.findById(request.idRoom).get()
        val entity = repository.save(mapper.requestToEntity(request, movie, room))

        return mapper.entityToResponse(entity)
    }

    fun delete(id: UUID){
        repository.deleteById(id)
    }
}