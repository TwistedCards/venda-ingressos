package com.example.venda_ingressos.controller.response

import com.example.venda_ingressos.dto.SaleDto
import java.util.*

class ClientResponse (
    val id: UUID? = null,
    val name: String,
    val cpf: String,
    val sale: SaleDto
)