package com.example.venda_ingressos.service

import com.example.venda_ingressos.controller.request.SessionRequest
import com.example.venda_ingressos.controller.request.paged.PagedRequest
import com.example.venda_ingressos.controller.response.SessionResponse
import com.example.venda_ingressos.entities.PriceTicket
import com.example.venda_ingressos.mapper.SessionMapper
import com.example.venda_ingressos.repository.PriceTicketRepository
import com.example.venda_ingressos.repository.SessionRepository
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class SessionService(
    val repository: SessionRepository,
    val mapper: SessionMapper,
    val priceTicketRepository: PriceTicketRepository
) {

    fun save(request: SessionRequest): SessionResponse {
        val entity = repository.save(mapper.requestToEntity(request))

        val priceEntitySave = request.prices.map {
            priceTicketRepository.save(
                PriceTicket(price = it.price, type = it.type, quantityTickets = it.quantityTickets, session = entity)
            )
        }

        entity.priceTickets.addAll(priceEntitySave)

        repository.save(entity)

        return mapper.entityToResponse(entity)
    }

    fun findAll(pagedRequest: PagedRequest): Page<SessionResponse> {
        return repository.findAll(pagedRequest.pageable()).map { mapper.entityToResponse(it) }
    }

}