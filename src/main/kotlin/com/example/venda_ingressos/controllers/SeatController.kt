package com.example.venda_ingressos.controllers

import com.example.venda_ingressos.controllers.responses.SeatResponse
import com.example.venda_ingressos.services.SeatService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/seats")
class SeatController(
    private val service: SeatService
) {

    @GetMapping
    fun getAll(): ResponseEntity<List<SeatResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll())
    }

}