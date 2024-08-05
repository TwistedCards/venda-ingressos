package com.example.venda_ingressos.controller

import com.example.venda_ingressos.dto.SaleDTO
import com.example.venda_ingressos.kafka.Producer
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/kafka", consumes = ["application/json"])
class SaleController(
    private val process: Producer
) {

    @PostMapping("/producer")
    @ResponseStatus(value = HttpStatus.OK)
    fun saleProcess(@RequestBody saleDTO: SaleDTO) {
        process.sendMensagens(saleDTO.client, saleDTO.numberOfTickets)
    }

}