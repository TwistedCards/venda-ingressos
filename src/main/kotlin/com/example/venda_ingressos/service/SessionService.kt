package com.example.venda_ingressos.service

import com.example.venda_ingressos.dto.SessionDto
import com.example.venda_ingressos.entities.Session
import com.example.venda_ingressos.mapper.SessionMapper
import com.example.venda_ingressos.repository.SessionRepository
import org.springframework.stereotype.Service

@Service
class SessionService(
    val repository: SessionRepository,
    val mapper: SessionMapper
) {

    fun save(sessionDto: SessionDto): Session {
        return repository.save(mapper.toEntity(sessionDto))
    }

}