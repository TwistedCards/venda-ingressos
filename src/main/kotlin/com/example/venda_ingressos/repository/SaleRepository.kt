package com.example.venda_ingressos.repository

import com.example.venda_ingressos.entities.Sale
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SaleRepository : JpaRepository<Sale, UUID> {
}