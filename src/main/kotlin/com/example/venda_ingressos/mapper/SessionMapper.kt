package com.example.venda_ingressos.mapper

import com.example.venda_ingressos.dto.SessionDto
import com.example.venda_ingressos.entities.Session
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Component
class SessionMapper {

    fun toEntity(dto: SessionDto): Session {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        return Session(
            name = dto.name,
            quantityTickets = dto.quantityTickets,
            datePresentation = LocalDate.parse(dto.datePresentation, formatter)
        )
    }

}