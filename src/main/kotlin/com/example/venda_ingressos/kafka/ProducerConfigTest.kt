package com.example.venda_ingressos.kafka

import com.example.venda_ingressos.entities.Sale
import com.example.venda_ingressos.serializer.SaleSerializer
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.util.*

@Component
class ProducerConfigTest(
    private val kafkaTemplate: KafkaTemplate<String, Sale>,
    @Value("\${spring.app.external.topic.name}") private val topicName: String
) {

    fun send(message: Sale){
        kafkaTemplate.send(topicName, message)
    }

//    fun config(): Properties {
//        val properties = Properties()
//
//        properties[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
//        properties[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = SaleSerializer::class.qualifiedName
//        properties[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = SaleSerializer::class.qualifiedName
//
//        return properties
//    }

}