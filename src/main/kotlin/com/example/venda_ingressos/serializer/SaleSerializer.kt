package com.example.venda_ingressos.serializer

import com.example.venda_ingressos.entities.Sale
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.serialization.Serializer

internal class SaleSerializer : Serializer<Sale> {
    override fun serialize(topic: String?, venda: Sale?): ByteArray {
        return ObjectMapper().writeValueAsBytes(venda)
    }
}