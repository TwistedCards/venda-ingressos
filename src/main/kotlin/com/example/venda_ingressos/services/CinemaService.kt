package com.example.venda_ingressos.services

import com.example.venda_ingressos.controllers.models.CinemaModel
import com.example.venda_ingressos.controllers.requests.CinemaRequest
import com.example.venda_ingressos.controllers.responses.CinemaResponse
import com.example.venda_ingressos.entities.CinemaEntity
import com.example.venda_ingressos.exceptions.IllegalArgumentException
import com.example.venda_ingressos.mappers.CinemaMapper
import com.example.venda_ingressos.repositorys.CinemaRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import java.util.*
import com.example.venda_ingressos.exceptions.DataIntegrityViolationException as DataIntegrityViolationExceptionLocal

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

            if(request.name.isEmpty()){
                throw IllegalArgumentException("m:save, msg:Name is obligatory")
            }

            if(request.phone.isEmpty()){
                throw IllegalArgumentException("m:save, msg:Phone is obligatory")
            }

            val entitySave = repository.save(mapper.requestToEntity(request))
            return mapper.entityToResponse(entitySave)
        } catch (e: DataIntegrityViolationException) {
            throw DataIntegrityViolationExceptionLocal(
                "m:save, msg:The phone ${request.phone}' already exists on the base"
            )
        }
    }

    fun delete(id: UUID) {
        repository.deleteById(id)
    }

    fun getAll(): List<CinemaModel> {
        return mapper.entityToListModel(repository.findAll())
    }
}