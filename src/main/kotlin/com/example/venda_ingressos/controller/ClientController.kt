package com.example.venda_ingressos.controller

import com.example.venda_ingressos.controller.request.paged.PagedRequest
import com.example.venda_ingressos.controller.response.paged.ClientPagedResponse
import com.example.venda_ingressos.controller.response.ClientResponse
import com.example.venda_ingressos.service.ClientService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/clients")
class ClientController(
    private val service: ClientService
) {

    @GetMapping
    fun getAllClients(
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

    @GetMapping("/{id}")
    fun getClientById(@RequestParam id: UUID): ResponseEntity<ClientResponse> {
        val entity = service.findById(id)
        return ResponseEntity.status(HttpStatus.OK).body(entity)
    }

}