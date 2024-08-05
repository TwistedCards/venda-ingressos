package com.example.venda_ingressos.kafka

import com.example.venda_ingressos.serializer.SaleSerializer
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProducerConfig {

    fun config(): Properties {
        val properties = Properties()

        properties[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
        properties[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = SaleSerializer::class.qualifiedName
        properties[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = SaleSerializer::class.qualifiedName

        return properties
    }

}