package com.example.venda_ingressos.kafka

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class KafkaMessageProducer{

    @Autowired
    lateinit var kafkaTemplate: KafkaTemplate<String, String>

    @Value("\${spring.app.external.topic.name}")
    lateinit var topicName: String

    fun send(message: String){
        kafkaTemplate.send(topicName, message)
    }

}