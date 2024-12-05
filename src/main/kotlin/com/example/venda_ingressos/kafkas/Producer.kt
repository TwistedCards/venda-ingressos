package com.example.venda_ingressos.kafkas

import org.springframework.stereotype.Component

@Component
class Producer(
//    val saleService: SaleService,
//    val mapper: SaleMapper,
    val kafkaMessageProducer: KafkaMessageProducer
) {

//    fun sendMessage(request: SaleRequest): SaleResponse {
//        try {
//            val sale = saleService.generateSale(request)
//            val json = ObjectMapper().writeValueAsString(sale)
//
//            kafkaMessageProducer.send(json)
//            Thread.sleep(200)
//            return mapper.entityToResponse(sale)
//        } catch (e: Exception) {
//            throw Exception("Error: $e")
//        }
//    }

}