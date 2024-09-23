package com.example.venda_ingressos.controller

import com.example.venda_ingressos.controller.request.paged.PagedRequest
import com.example.venda_ingressos.controller.request.SaleRequest
import com.example.venda_ingressos.controller.response.paged.SalePagedResponse
import com.example.venda_ingressos.controller.response.SaleResponse
import com.example.venda_ingressos.kafka.Producer
import com.example.venda_ingressos.service.SaleService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sales")
class SaleController(
    val service: SaleService,
    val producer: Producer
) {

    @PostMapping("/teste")
    fun saleProcess(){
        producer.sendMessageTest()
    }

    @PostMapping
    fun saleProcess(@RequestBody request: SaleRequest): ResponseEntity<SaleResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(producer.sendMessage(request))
    }

    @GetMapping
    fun getAllSales(
        @RequestParam(defaultValue = "10") size: Int?,
        @RequestParam(defaultValue = "0") offset: Int?
    ): ResponseEntity<SalePagedResponse> {
        val pagedRequest = PagedRequest(
            size = size!!,
            offset = offset!!
        )

        val page = service.findAll(pagedRequest)

        return ResponseEntity.status(HttpStatus.OK).body(SalePagedResponse(page, offset))
    }

}