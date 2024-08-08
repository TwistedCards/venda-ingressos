package com.example.venda_ingressos.mapper

import com.example.venda_ingressos.dto.SessionDto
import com.example.venda_ingressos.entities.Session
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter



@Service
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