package com.example.venda_ingressos.service

import com.example.venda_ingressos.controller.request.ClientRequest
import com.example.venda_ingressos.controller.request.paged.PagedRequest
import com.example.venda_ingressos.controller.response.ClientResponse
import com.example.venda_ingressos.entities.Client
import com.example.venda_ingressos.mapper.ClientMapper
import com.example.venda_ingressos.repository.ClientRepository
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.util.*

@Service
class ClientService(
    private val repository: ClientRepository,
    private val mapper: ClientMapper
) {

    fun findById(id: UUID): Client {
        return repository.findById(id).get()
    }

    fun findAll(pagedRequest: PagedRequest): Page<ClientResponse> {
        return repository.findAll(pagedRequest.pageable()).map { mapper.entityToResponse(it) }
    }

    fun save(request: ClientRequest): ClientResponse {
        val savedEntity = repository.save(mapper.requestToEntity(request))
        return mapper.entityToResponse(savedEntity)
    }

    fun edit(request: ClientRequest): ClientResponse {
        val entity = repository.findByCpf(request.cpf)

        entity.cpf = request.cpf
        entity.name = request.name

        val savedEntity = repository.save(entity)

        return mapper.entityToResponse(savedEntity)
    }

    fun delete(id: UUID){
        return repository.deleteById(id)
    }

}