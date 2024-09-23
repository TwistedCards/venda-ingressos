package com.example.venda_ingressos.service

import com.example.venda_ingressos.entities.Room
import com.example.venda_ingressos.repository.RoomRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class RoomService(
    private val repository: RoomRepository
) {

    fun findById(id: UUID): Room {
        return repository.findById(id).get()
    }

}