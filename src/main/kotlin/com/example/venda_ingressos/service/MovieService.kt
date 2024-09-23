package com.example.venda_ingressos.service

import com.example.venda_ingressos.entities.Movie
import com.example.venda_ingressos.repository.MovieRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class MovieService(
    private val repository: MovieRepository
) {

    fun findById(id: UUID): Movie {
        return repository.findById(id).get()
    }
}