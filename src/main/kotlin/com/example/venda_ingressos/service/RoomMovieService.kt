package com.example.venda_ingressos.service

import com.example.venda_ingressos.entities.RoomMovie
import com.example.venda_ingressos.repository.RoomMovieRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class RoomMovieService(
    private val repository: RoomMovieRepository
) {

    fun findById(id: UUID): RoomMovie {
        return repository.findById(id).get()
    }
}