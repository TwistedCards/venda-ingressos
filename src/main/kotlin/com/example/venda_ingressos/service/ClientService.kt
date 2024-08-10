package com.example.venda_ingressos.service

import com.example.venda_ingressos.dto.SaleDTO
import com.example.venda_ingressos.entities.Client
import com.example.venda_ingressos.entities.Sale
import com.example.venda_ingressos.repository.ClientRepository
import org.springframework.stereotype.Service

@Service
class ClientService(
    val repository: ClientRepository
) {

    fun getAll(): List<Client> {
        return repository.findAll()
    }

    fun saveClient(saleDTO: SaleDTO, saleEntity: Sale) {

        val listClient = saleDTO.clients.map {
            Client(
                name = it.name,
                cpf = it.cpf,
                sale = saleEntity
            )
        }

        repository.saveAll(listClient)
    }

}