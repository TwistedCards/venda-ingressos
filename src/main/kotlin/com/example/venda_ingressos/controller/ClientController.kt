package com.example.venda_ingressos.controller

import com.example.venda_ingressos.controller.request.ClientRequest
import com.example.venda_ingressos.controller.request.paged.PagedRequest
import com.example.venda_ingressos.controller.response.ClientResponse
import com.example.venda_ingressos.controller.response.paged.ClientPagedResponse
import com.example.venda_ingressos.entities.Client
import com.example.venda_ingressos.service.ClientService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/clients")
class ClientController(
//    val producer: Producer
    private val service: ClientService
) {

//    @PostMapping
//    fun saleProcess(@RequestBody request: SaleRequest): ResponseEntity<SaleResponse> {
//        return ResponseEntity.status(HttpStatus.OK).body(producer.sendMessage(request))
//    }

    @PostMapping
    fun save(@RequestBody request: ClientRequest): ResponseEntity<ClientResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(service.save(request))
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

    @PutMapping("/{id}")
    fun delete(@RequestParam id: UUID) {
        service.delete(id)
    }

}