package com.example.venda_ingressos.kafka

import com.example.venda_ingressos.dto.SaleDto
import com.example.venda_ingressos.entities.Sale
import com.example.venda_ingressos.service.SaleService
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.stereotype.Component

@Component
class Producer(
    val saleService: SaleService,
    val producerConfig: ProducerConfig
) {

    fun sendMessage(saleDTO: SaleDto): Sale {
        try {
            val producer = KafkaProducer<String, Sale>(producerConfig.config())

            val sale = saleService.generateSale(saleDTO)
            val record = ProducerRecord<String, Sale>("venda-ingressos-teste", sale)

            producer.send(record)
            Thread.sleep(200)
            return sale
        } catch (e: Exception) {
            throw Exception("Error: $e")
        }
    }

}