package com.example.venda_ingressos.mapper

import com.example.venda_ingressos.controller.request.SessionRequest
import com.example.venda_ingressos.controller.response.SessionResponse
import com.example.venda_ingressos.dto.SessionDto
import com.example.venda_ingressos.entities.Session
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Component
class SessionMapper {

    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    fun requestToEntity(request: SessionRequest): Session {
        return Session(
            name = request.name,
            quantityTickets = request.quantityTickets,
            datePresentation = LocalDate.parse(request.datePresentation, formatter),
            valueOfTickets = request.valueOfTickets
        )
    }

    fun entityToResponse(entity: Session): SessionResponse {
        return SessionResponse(
            name = entity.name,
            quantityTickets = entity.quantityTickets,
            datePresentation = entity.datePresentation.format(formatter),
            valueOfTickets = entity.valueOfTickets
        )
    }

    fun dtoToEntity(dto: SessionDto): Session {
        return Session(
            name = dto.name,
            quantityTickets = dto.quantityTickets,
            datePresentation = LocalDate.parse(dto.datePresentation, formatter),
            valueOfTickets = dto.valueOfTickets
        )
    }

    fun entityToDto(entity: Session): SessionDto {
        return SessionDto(
            name = entity.name,
            quantityTickets = entity.quantityTickets,
            datePresentation = entity.datePresentation.format(formatter),
            valueOfTickets = entity.valueOfTickets!!
        )
    }

}