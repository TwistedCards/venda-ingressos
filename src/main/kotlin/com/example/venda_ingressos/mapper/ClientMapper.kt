package com.example.venda_ingressos.mapper

import com.example.venda_ingressos.controller.request.ClientRequest
import com.example.venda_ingressos.controller.response.ClientResponse
import com.example.venda_ingressos.entities.Client
import org.springframework.stereotype.Component

@Component
class ClientMapper {

    fun entityToResponse(entity: Client): ClientResponse {
        return ClientResponse(
            name = entity.name,
            cpf = entity.cpf
        )
    }

    fun requestToEntity(request: ClientRequest): Client {
        return Client(
            name = request.name,
            cpf = request.cpf
        )
    }

}