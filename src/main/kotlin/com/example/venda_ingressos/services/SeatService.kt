package com.example.venda_ingressos.services

import com.example.venda_ingressos.controllers.responses.SeatResponse
import com.example.venda_ingressos.enums.CategoryEnum
import com.example.venda_ingressos.entities.RoomEntity
import com.example.venda_ingressos.entities.SeatEntity
import com.example.venda_ingressos.enums.StatusEnum
import com.example.venda_ingressos.exceptions.IllegalArgumentException
import com.example.venda_ingressos.repositorys.SeatRepository
import com.example.venda_ingressos.repositorys.SeatSessionRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class SeatService(
    private val repository: SeatRepository,
    private val seatSessionRepository: SeatSessionRepository
) {

    fun verifyIfSeatIsNotOccupied(idSeat: UUID, idSession: UUID) {
        val seatSessionEntity = seatSessionRepository.findBySeatIdAndSessionId(idSeat, idSession)

        if (seatSessionEntity.status == StatusEnum.OCCUPIED) {
            throw IllegalArgumentException("O assento ${seatSessionEntity.seat.codSeat} está ocupado.")
        }
    }

    fun findAllSeats(): List<SeatResponse> {
        val allSeats = repository.findAll()

        val listSeatResponse: MutableList<SeatResponse> = mutableListOf()

        allSeats.map {
            listSeatResponse.add(
                SeatResponse(
                    codSeat = it.codSeat,
                    category = it.category.name,
                    status = it.seatSessions?.firstOrNull { x -> x.seat.id == it.id }!!.status.name
                )
            )
        }

        return listSeatResponse
    }

    @Transactional
    fun save(qtdSeat: Int, roomEntity: RoomEntity) {
        var i = 0

        while (i < qtdSeat) {
            val rand = ('A'..'Z').random()

            val entity = SeatEntity(
                codSeat = "$i" + rand,
                category = CategoryEnum.NORMAL,
                room = roomEntity
            )

            repository.save(entity)

            i++
        }
    }

    fun findSeatByRoomId(roomId: UUID): MutableList<SeatEntity> {
        return repository.findByRoomId(roomId)
    }

}