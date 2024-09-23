package com.example.venda_ingressos.kafka

import com.example.venda_ingressos.controller.request.SaleRequest
import com.example.venda_ingressos.controller.response.SaleResponse
import com.example.venda_ingressos.entities.Sale
import com.example.venda_ingressos.mapper.SaleMapper
import com.example.venda_ingressos.service.SaleService
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.util.*

@Component
class Producer(
    val saleService: SaleService,
    val producerConfig: ProducerConfig,
    val mapper: SaleMapper,
    val kafkaMessageProducer: KafkaMessageProducer
) {

    fun sendMessage(request: SaleRequest): SaleResponse {
        try {
//            val producer = KafkaProducer<String, Sale>(producerConfig.config())

            val sale = saleService.generateSale(request)
//            val record = ProducerRecord<String, Sale>("venda-ingressos-teste", sale)

            val json = ObjectMapper().writeValueAsString(sale)

            kafkaMessageProducer.send(json)
//            producer.send(record)
            Thread.sleep(200)
            return mapper.entityToResponse(sale)
        } catch (e: Exception) {
            throw Exception("Error: $e")
        }
    }

}