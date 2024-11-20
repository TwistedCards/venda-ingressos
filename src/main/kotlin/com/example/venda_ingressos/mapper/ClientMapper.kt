package com.example.venda_ingressos.mapper

import com.example.venda_ingressos.controller.request.ClientRequest
import com.example.venda_ingressos.controller.response.ClientResponse
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