package com.example.venda_ingressos.controllers

import com.example.venda_ingressos.controllers.models.SessionModel
import com.example.venda_ingressos.controllers.requests.SessionRequest
import com.example.venda_ingressos.controllers.responses.SessionResponse
import com.example.venda_ingressos.services.SessionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/sessions")
class SessionController(
    private val service: SessionService
) {

    @PostMapping
    fun save(@RequestBody request: SessionRequest): ResponseEntity<SessionResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request))
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): ResponseEntity<SessionModel>{
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id))
    }

}