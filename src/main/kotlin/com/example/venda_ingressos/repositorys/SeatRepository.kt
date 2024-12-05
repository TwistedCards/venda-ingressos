package com.example.venda_ingressos.repositorys

import com.example.venda_ingressos.entities.SeatEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SeatRepository : JpaRepository<SeatEntity, UUID> {

    fun findByRoomId(id: UUID): MutableList<SeatEntity>

}