package com.example.venda_ingressos.services

import com.example.venda_ingressos.controllers.requests.ClientRequest
import com.example.venda_ingressos.controllers.requests.paged.PagedRequest
import com.example.venda_ingressos.controllers.responses.ClientResponse
import com.example.venda_ingressos.entities.ClientEntity
import com.example.venda_ingressos.exceptions.EntityNotFoundException
import com.example.venda_ingressos.exceptions.IllegalArgumentException
import com.example.venda_ingressos.mappers.ClientMapper
import com.example.venda_ingressos.repositorys.ClientRepository
import org.springframework.dao.EmptyResultDataAccessException
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
        val entity = try {
            repository.findByCpf(request.cpf)
        } catch (e: EmptyResultDataAccessException) {
            throw EntityNotFoundException("m=edit, msg=This CPF: {${request.cpf}} don't exist")
        }

        if (request.newCpf.isNullOrEmpty()) {
            throw IllegalArgumentException("m=edit, msg=CPF cannot be null")
        }

        if (request.name.isNullOrEmpty()) {
            throw IllegalArgumentException("m=edit, msg=Name cannot be null")
        }

        entity.cpf = request.newCpf
        entity.name = request.name

        val savedEntity = repository.save(entity)

        return mapper.entityToResponse(savedEntity)
    }

    fun delete(id: UUID) {
        return repository.deleteById(id)
    }

    fun editSomeData(request: ClientRequest): ClientResponse{
        val entity = try {
            repository.findByCpf(request.cpf)
        } catch (e: EmptyResultDataAccessException) {
            throw EntityNotFoundException("m=edit, msg=This CPF: {${request.cpf}} don't exist")
        }

        if (request.newCpf!!.isNotEmpty()) {
            entity.cpf = request.newCpf
        }

        if (request.name!!.isNotEmpty()) {
            entity.name = request.name
        }

        val savedEntity = repository.save(entity)

        return mapper.entityToResponse(savedEntity)
    }

}