package com.example.venda_ingressos.controllers

import com.example.venda_ingressos.controllers.models.CinemaModel
import com.example.venda_ingressos.controllers.requests.CinemaRequest
import com.example.venda_ingressos.controllers.responses.CinemaResponse
import com.example.venda_ingressos.services.CinemaService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/cinemas")
class CinemaController(
    private val service: CinemaService
) {

    @PostMapping
    fun save(@RequestBody request: CinemaRequest): ResponseEntity<CinemaResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: UUID) {
        service.delete(id)
    }

    @GetMapping
    fun getAll(): ResponseEntity<List<CinemaModel>>{
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll())
    }

}