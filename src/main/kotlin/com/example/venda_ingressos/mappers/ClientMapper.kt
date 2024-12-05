package com.example.venda_ingressos.mappers

import com.example.venda_ingressos.controllers.requests.ClientRequest
import com.example.venda_ingressos.controllers.responses.ClientResponse
import com.example.venda_ingressos.entities.ClientEntity
import org.springframework.stereotype.Component

@Component
class ClientMapper {

    fun entityToResponse(entity: ClientEntity): ClientResponse {
        return ClientResponse(
            id = entity.id!!,
            name = entity.name,
            cpf = entity.cpf
        )
    }

    fun requestToEntity(request: ClientRequest): ClientEntity {
        return ClientEntity(
            name = request.name,
            cpf = request.cpf
        )
    }

}