package com.example.venda_ingressos.service

import com.example.venda_ingressos.entities.Client
import com.example.venda_ingressos.repository.ClientRepository
import java.util.*

class ClientService(
    private val repository: ClientRepository
) {

    fun findById(id: UUID): Client {
        return repository.findById(id).get()
    }

}