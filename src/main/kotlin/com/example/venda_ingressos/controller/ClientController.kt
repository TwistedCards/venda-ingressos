package com.example.venda_ingressos.controller

import com.example.venda_ingressos.entities.Client
import com.example.venda_ingressos.service.ClientService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/clients", consumes = ["application/json"])
class ClientController(
    private val service: ClientService
) {

    @GetMapping
    fun getAll(): List<Client> {
        return service.getAll()
    }

}