package com.example.venda_ingressos.controller

import com.example.venda_ingressos.controller.model.SessionModel
import com.example.venda_ingressos.controller.request.SessionRequest
import com.example.venda_ingressos.controller.response.SessionResponse
import com.example.venda_ingressos.entities.SessionEntity
import com.example.venda_ingressos.service.SessionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
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
    fun getById(id: UUID): ResponseEntity<SessionModel>{
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id))
    }

    @GetMapping("/teste/{id}")
    fun teste(@PathVariable id: UUID): ResponseEntity<SessionEntity>{
        return ResponseEntity.status(HttpStatus.OK).body(service.getSeatSession(id))
    }

}