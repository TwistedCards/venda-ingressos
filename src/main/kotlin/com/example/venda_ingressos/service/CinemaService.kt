package com.example.venda_ingressos.service

import com.example.venda_ingressos.controller.request.CinemaRequest
import com.example.venda_ingressos.controller.response.CinemaResponse
import com.example.venda_ingressos.entities.CinemaEntity
import com.example.venda_ingressos.exceptions.DataIntegrityViolationException as DataIntegrityViolationExceptionLocal
import com.example.venda_ingressos.mapper.CinemaMapper
import com.example.venda_ingressos.repository.CinemaRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
//import java.sql.SQLIntegrityConstraintViolationException
import java.util.*

@Service
class CinemaService(
    private val repository: CinemaRepository,
    private val mapper: CinemaMapper
) {
    fun findById(id: UUID): CinemaEntity {
        return repository.findById(id).get()
    }

    fun save(request: CinemaRequest): CinemaResponse {
        try {
            val entitySave = repository.save(mapper.requestToEntity(request))
            return mapper.entityToResponse(entitySave)
        } catch (e: DataIntegrityViolationException) {
            throw DataIntegrityViolationExceptionLocal(
                "m:save, msg:O telefone '${request.phone}' já existe na base."
            )
        }
    }

    fun delete(id: UUID) {
        repository.deleteById(id)
    }
}