package com.example.venda_ingressos.controller

import com.example.venda_ingressos.dto.SaleDTO
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
    // TODO retirar esse ResponseStatus e colocar no return ()
    @ResponseStatus(value = HttpStatus.OK)
    fun saleProcess(@RequestBody saleDTO: SaleDTO) {
        return producer.sendMessage(saleDTO)
    }

    @GetMapping
    fun getAllSale(): List<Sale> {
        return service.getAll()
    }

}