package com.example.venda_ingressos.controller

import com.example.venda_ingressos.dto.SaleDto
import com.example.venda_ingressos.entities.Sale
import com.example.venda_ingressos.kafka.Producer
import com.example.venda_ingressos.service.SaleService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sale", consumes = ["application/json"])
class SaleController(
    val service: SaleService,
    val producer: Producer
) {

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    fun saleProcess(@RequestBody saleDTO: SaleDto): Sale {
        return producer.sendMessage(saleDTO)
    }

    @GetMapping
    fun getAllSale(): List<Sale> {
        return service.getAll()
    }

}