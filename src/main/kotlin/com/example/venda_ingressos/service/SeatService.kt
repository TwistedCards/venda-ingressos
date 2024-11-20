package com.example.venda_ingressos.service

import com.example.venda_ingressos.controller.request.CinemaRequest
import com.example.venda_ingressos.controller.response.CinemaResponse
import com.example.venda_ingressos.controller.response.SeatResponse
import com.example.venda_ingressos.entities.Seat
import com.example.venda_ingressos.entities.StatusEnum
import com.example.venda_ingressos.exceptions.EntityNotFoundException
import com.example.venda_ingressos.exceptions.IllegalArgumentException
import com.example.venda_ingressos.repository.SeatRepository
import com.example.venda_ingressos.repository.SeatSessionRepository
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
}