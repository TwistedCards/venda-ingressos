package com.example.venda_ingressos.kafka

import com.example.venda_ingressos.entities.Sale
import com.example.venda_ingressos.service.SaleService
import jakarta.transaction.Transactional
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.stereotype.Service

@Service
class Producer(
    val saleService: SaleService,
    val producerConfig: ProducerConfig
) {

    @Transactional
    fun sendMensagens(client: String, numberOfTickets: Int) {
        try {
            val producer = KafkaProducer<String, Sale>(producerConfig.config())

            val sale = saleService.generateSale(client, numberOfTickets)
            val record = ProducerRecord<String, Sale>("venda-ingressos-teste", sale)

            producer.send(record)
            Thread.sleep(200)
        } catch (e: Exception) {
            throw Exception("Error: $e")
        }
    }

}