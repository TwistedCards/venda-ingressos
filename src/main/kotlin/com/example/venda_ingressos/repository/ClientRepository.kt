package com.example.venda_ingressos.repository

import com.example.venda_ingressos.entities.Client
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ClientRepository : JpaRepository<Client, UUID>{
}