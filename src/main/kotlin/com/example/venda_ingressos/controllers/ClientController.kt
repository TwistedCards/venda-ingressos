package com.example.venda_ingressos.controllers

import com.example.venda_ingressos.controllers.requests.ClientRequest
import com.example.venda_ingressos.controllers.requests.paged.PagedRequest
import com.example.venda_ingressos.controllers.responses.ClientResponse
import com.example.venda_ingressos.controllers.responses.paged.ClientPagedResponse
import com.example.venda_ingressos.services.ClientService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/clients")
class ClientController(
    private val service: ClientService
) {

    @PostMapping
    fun save(@RequestBody request: ClientRequest): ResponseEntity<ClientResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request))
    }

    @GetMapping
    fun getAll(
        @RequestParam(defaultValue = "10") size: Int?,
        @RequestParam(defaultValue = "0") offset: Int?
    ): ResponseEntity<ClientPagedResponse> {
        val pagedRequest = PagedRequest(
            size = size!!,
            offset = offset!!
        )

        val page = service.findAll(pagedRequest)

        return ResponseEntity.status(HttpStatus.OK).body(ClientPagedResponse(page, offset))
    }

    @PutMapping
    fun editFullClient(@RequestBody request: ClientRequest): ResponseEntity<ClientResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(service.edit(request))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@RequestParam id: UUID) {
        service.delete(id)
    }

}