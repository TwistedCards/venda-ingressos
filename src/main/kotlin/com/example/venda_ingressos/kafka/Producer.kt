package com.example.venda_ingressos.kafka

import com.example.venda_ingressos.dto.SaleDTO
import com.example.venda_ingressos.entities.Sale
import com.example.venda_ingressos.service.SaleService
import jakarta.transaction.Transactional
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Component
class Producer(
    val saleService: SaleService,
    val producerConfig: ProducerConfig
) {

    fun sendMessage(saleDTO: SaleDTO) {
        try {
            val producer = KafkaProducer<String, Sale>(producerConfig.config())

            val sale = saleService.generateSale(saleDTO)
            val record = ProducerRecord<String, Sale>("venda-ingressos-teste", sale)

            producer.send(record)
            Thread.sleep(200)
        } catch (e: Exception) {
            throw Exception("Error: $e")
        }
    }

}