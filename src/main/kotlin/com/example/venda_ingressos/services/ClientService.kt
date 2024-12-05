package com.example.venda_ingressos.services

import com.example.venda_ingressos.controllers.requests.ClientRequest
import com.example.venda_ingressos.controllers.requests.paged.PagedRequest
import com.example.venda_ingressos.controllers.responses.ClientResponse
import com.example.venda_ingressos.entities.ClientEntity
import com.example.venda_ingressos.mappers.ClientMapper
import com.example.venda_ingressos.repositorys.ClientRepository
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.util.*

@Service
class ClientService(
    private val repository: ClientRepository,
    private val mapper: ClientMapper
) {

    fun findById(id: UUID): ClientEntity {
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