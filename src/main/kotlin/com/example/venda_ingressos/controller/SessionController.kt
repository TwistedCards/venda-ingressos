package com.example.venda_ingressos.controller

import com.example.venda_ingressos.controller.request.SessionRequest
import com.example.venda_ingressos.controller.response.SessionResponse
import com.example.venda_ingressos.service.SessionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sessions")
class SessionController(
    private val service: SessionService
) {

    @PostMapping
    fun save(@RequestBody request: SessionRequest): ResponseEntity<SessionResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request))
    }

//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    fun delete(@PathVariable id: UUID) {
//        service.delete(id)
//    }

}