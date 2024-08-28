package com.example.venda_ingressos.service

import com.example.venda_ingressos.controller.request.paged.PagedRequest
import com.example.venda_ingressos.controller.request.SaleRequest
import com.example.venda_ingressos.controller.response.ClientResponse
import com.example.venda_ingressos.entities.Sale
import com.example.venda_ingressos.mapper.ClientMapper
import com.example.venda_ingressos.repository.ClientRepository
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.util.*

@Service
class ClientService(
    val repository: ClientRepository,
    val mapper: ClientMapper
) {

    fun findAll(dto: PagedRequest): Page<ClientResponse> {
        return repository.findAll(dto.pageable()).map { mapper.entityToResponse(it) }
    }

    fun saveClientBySale(request: SaleRequest, saleEntity: Sale) {
        val listClient = request.clients!!.map {
            mapper.requestToEntity(it, saleEntity)
        }

        repository.saveAll(listClient)
    }

    fun findById(id: UUID): ClientResponse {
        return mapper.entityToResponse(repository.findById(id).get())
    }

}