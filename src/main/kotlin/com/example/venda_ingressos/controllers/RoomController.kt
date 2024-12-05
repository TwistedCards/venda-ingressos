package com.example.venda_ingressos.controllers

import com.example.venda_ingressos.controllers.requests.RoomRequest
import com.example.venda_ingressos.controllers.responses.RoomResponse
import com.example.venda_ingressos.entities.RoomEntity
import com.example.venda_ingressos.services.RoomService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/rooms")
class RoomController(
    private val service: RoomService
) {

    @PostMapping
    fun save(@RequestBody request: RoomRequest): ResponseEntity<RoomResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: UUID) {
        service.delete(id)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): RoomEntity {
        return service.findById(id)
    }

}