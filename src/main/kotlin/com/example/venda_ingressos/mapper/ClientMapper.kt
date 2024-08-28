package com.example.venda_ingressos.mapper

import com.example.venda_ingressos.controller.request.ClientRequest
import com.example.venda_ingressos.controller.response.ClientResponse
import com.example.venda_ingressos.dto.ClientDto
import com.example.venda_ingressos.dto.SaleDto
import com.example.venda_ingressos.entities.Client
import com.example.venda_ingressos.entities.Sale
import org.springframework.stereotype.Component

@Component
class ClientMapper(
    private val saleMapper: SaleMapper
) {

    fun entityToResponse(entity: Client): ClientResponse {
        return ClientResponse(
            id = entity.id,
            name = entity.name,
            cpf = entity.cpf,
            sale = SaleDto(
                nameOfSession = entity.sale.nameOfSession,
                numberOfTickets = entity.sale.numberOfTickets,
                totalValue = entity.sale.totalValue,
                status = entity.sale.status
            )
        )
    }

    fun requestToEntity(request: ClientRequest, saleEntity: Sale): Client {
        return Client(
            name = request.name,
            cpf = request.cpf,
            sale = saleEntity
        )
    }

    fun entityToDto(entity: Client): ClientDto {
        return ClientDto(
            name = entity.name,
            cpf = entity.cpf
        )
    }

    fun dtoToEntity(dto: ClientDto, saleEntity: Sale): Client {
        return Client(
            name = dto.name,
            cpf = dto.cpf,
            sale = saleEntity
        )
    }

}