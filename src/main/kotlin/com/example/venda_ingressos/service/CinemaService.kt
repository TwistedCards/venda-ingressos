package com.example.venda_ingressos.service

import com.example.venda_ingressos.entities.Cinema
import com.example.venda_ingressos.repository.CinemaRepository
import java.util.*

class CinemaService(
    private val repository: CinemaRepository
) {

    fun findById(id: UUID): Cinema {
        return repository.findById(id).get()
    }
}