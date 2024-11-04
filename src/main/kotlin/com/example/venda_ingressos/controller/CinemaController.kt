package com.example.venda_ingressos.controller

import com.example.venda_ingressos.controller.request.CinemaRequest
import com.example.venda_ingressos.controller.request.ClientRequest
import com.example.venda_ingressos.controller.request.paged.PagedRequest
import com.example.venda_ingressos.controller.response.CinemaResponse
import com.example.venda_ingressos.controller.response.ClientResponse
import com.example.venda_ingressos.controller.response.paged.ClientPagedResponse
import com.example.venda_ingressos.service.CinemaService
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

}