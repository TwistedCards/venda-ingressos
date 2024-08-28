package com.example.venda_ingressos.mapper

import com.example.venda_ingressos.controller.response.SaleResponse
import com.example.venda_ingressos.dto.ClientDto
import com.example.venda_ingressos.dto.SaleDto
import com.example.venda_ingressos.entities.Sale
import com.example.venda_ingressos.entities.Session
import org.springframework.stereotype.Component

@Component
class SaleMapper{

    fun dtoToEntity(dto: SaleDto, session: Session? = null): Sale {
        return Sale(
            nameOfSession = dto.nameOfSession,
            numberOfTickets = dto.numberOfTickets,
            totalValue = dto.totalValue!!,
            status = dto.status,
            session = session!!
        )
    }

    fun entityToDto(entity: Sale): SaleDto {
        return SaleDto(
            nameOfSession = entity.nameOfSession,
            numberOfTickets = entity.numberOfTickets,
            totalValue = entity.totalValue,
            status = entity.status
        )
    }

    fun entityToResponse(entity: Sale): SaleResponse {
        return SaleResponse(
            nameOfSession = entity.nameOfSession,
            clients = entity.clients.map { ClientDto(name = it.name, cpf = it.cpf) }.toMutableList(),
            numberOfTickets = entity.numberOfTickets
        )
    }

}