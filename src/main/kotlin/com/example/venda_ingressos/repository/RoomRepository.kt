package com.example.venda_ingressos.repository

import com.example.venda_ingressos.entities.Room
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RoomRepository : JpaRepository<Room, UUID> {
}