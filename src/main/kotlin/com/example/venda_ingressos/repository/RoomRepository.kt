package com.example.venda_ingressos.repository

import com.example.venda_ingressos.entities.RoomEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RoomRepository : JpaRepository<RoomEntity, UUID> {
}