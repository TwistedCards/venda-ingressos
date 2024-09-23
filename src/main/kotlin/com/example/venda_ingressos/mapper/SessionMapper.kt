package com.example.venda_ingressos.mapper

import com.example.venda_ingressos.controller.request.SessionRequest
import com.example.venda_ingressos.controller.response.PriceTicketResponse
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
            datePresentation = LocalDate.parse(request.datePresentation, formatter)
        )
    }

    fun entityToResponse(entity: Session): SessionResponse {
        val sessionResponse = SessionResponse(
            name = entity.name,
            datePresentation = entity.datePresentation.format(formatter)
        )

        entity.priceTickets.forEach {
            sessionResponse.priceTickets.add(
                PriceTicketResponse(
                    price = it.price!!,
                    type = it.type!!,
                    quantityTickets = it.quantityTickets
                )
            )
        }

        return sessionResponse
    }

    fun dtoToEntity(dto: SessionDto): Session {
        return Session(
            name = dto.name,
            datePresentation = LocalDate.parse(dto.datePresentation, formatter)
        )
    }

    fun entityToDto(entity: Session): SessionDto {
        return SessionDto(
            name = entity.name,
            datePresentation = entity.datePresentation.format(formatter)
        )
    }

}