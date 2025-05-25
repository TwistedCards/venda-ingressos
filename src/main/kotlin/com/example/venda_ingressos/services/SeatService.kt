package com.example.venda_ingressos.services

import com.example.venda_ingressos.controllers.responses.SeatResponse
import com.example.venda_ingressos.entities.RoomEntity
import com.example.venda_ingressos.entities.SeatEntity
import com.example.venda_ingressos.enums.CategoryEnum
import com.example.venda_ingressos.enums.StatusEnum
import com.example.venda_ingressos.exceptions.IllegalArgumentException
import com.example.venda_ingressos.mappers.SeatMapper
import com.example.venda_ingressos.repositorys.SeatRepository
import com.example.venda_ingressos.repositorys.SeatSessionRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class SeatService(
    private val repository: SeatRepository,
    private val seatSessionRepository: SeatSessionRepository,
    private val mapper: SeatMapper,
    private val seatSessionService: SeatSessionService
) {

    fun verifyIfSeatIsNotOccupied(idSeat: UUID, idSession: UUID) {
        val seatSessionEntity = seatSessionRepository.findBySeatIdAndSessionId(idSeat, idSession)

        if (seatSessionEntity.status == StatusEnum.OCCUPIED) {
            throw IllegalArgumentException("O assento ${seatSessionEntity.seat?.codSeat} está ocupado.")
        }
    }

    @Transactional
    fun save(qtdSeat: Int, roomEntity: RoomEntity) {
        var i = 0

        while (i < qtdSeat) {
            val rand = ('A'..'Z').random()

            val entity = SeatEntity(
                codSeat = rand + "$i",
                category = CategoryEnum.NORMAL,
                room = roomEntity
            )

            val finalEntity = repository.saveAndFlush(entity)

            seatSessionService.save(seatEntity = finalEntity)

            i++
        }

    }

    fun findSeatByRoomId(roomId: UUID): MutableList<SeatEntity> {
        return repository.findByRoomId(roomId)
    }

    fun findAll(): List<SeatResponse> {
        return mapper.entityToResponse(repository.findAll())
    }

}