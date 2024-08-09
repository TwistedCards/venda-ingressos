package com.example.venda_ingressos.controller

import com.example.venda_ingressos.dto.SessionDto
import com.example.venda_ingressos.entities.Session
import com.example.venda_ingressos.service.SessionService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/session", consumes = ["application/json"])
class SessionController(
    private val service: SessionService
) {

    @PostMapping
    fun save(@RequestBody sessionDto: SessionDto): Session {
        return service.save(sessionDto)
    }

    @GetMapping
    fun getAll(): List<Session> {
        return service.getAll()
    }

}