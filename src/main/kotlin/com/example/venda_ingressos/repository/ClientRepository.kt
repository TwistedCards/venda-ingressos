package com.example.venda_ingressos.repository

import com.example.venda_ingressos.entities.ClientEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ClientRepository : JpaRepository<ClientEntity, UUID> {

    fun findByCpf(cpf: String): ClientEntity

}