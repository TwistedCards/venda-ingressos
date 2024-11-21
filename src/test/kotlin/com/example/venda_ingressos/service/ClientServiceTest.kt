package com.example.venda_ingressos.service

import com.example.venda_ingressos.controller.request.ClientRequest
import com.example.venda_ingressos.controller.request.paged.PagedRequest
import com.example.venda_ingressos.controller.response.ClientResponse
import com.example.venda_ingressos.entities.ClientEntity
import com.example.venda_ingressos.mapper.ClientMapper
import com.example.venda_ingressos.repository.ClientRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import java.util.*

@ExtendWith(MockKExtension::class)
class ClientServiceTest {

    @MockK
    private lateinit var repository: ClientRepository

    @MockK
    private lateinit var mapper: ClientMapper

    @InjectMockKs
    private lateinit var service: ClientService

    private lateinit var request: ClientRequest
    private lateinit var response: ClientResponse
    private lateinit var fakeEntity: ClientEntity

    @BeforeEach
    fun setUp() {
        request = ClientRequest(name = "Paulo", cpf = "111.111.111-11")
        fakeEntity = ClientEntity(
            id = UUID.fromString("205847e8-7c73-4257-8d5f-9b2c61f9838b"),
            name = "Paulo",
            cpf = "111.111.111-11"
        )
        response = ClientResponse(
            id = UUID.fromString("205847e8-7c73-4257-8d5f-9b2c61f9838b"),
            name = "Paulo",
            cpf = "111.111.111-11"
        )
    }

    @Test
    @DisplayName("Efetua o cadastro do cliente")
    fun `register the client`() {
        every { mapper.requestToEntity(request) } returns fakeEntity
        every { repository.save(fakeEntity) } returns fakeEntity
        every { mapper.entityToResponse(fakeEntity) } returns response

        val client = service.save(request)

        verify(exactly = 1) { repository.save(fakeEntity) }
        verify(exactly = 1) { mapper.requestToEntity(request) }
        verify(exactly = 1) { mapper.entityToResponse(fakeEntity) }
        assertEquals(response, client)
    }

    @Test
    @DisplayName("Busca o client baseado no ID passado")
    fun `search for the client based on the ID provided`() {
        every { repository.findById(any()).get() } returns fakeEntity

        val client = service.findById(fakeEntity.id!!)

        verify(exactly = 1) { repository.findById(any()) }
        assertEquals(fakeEntity, client)
    }

    @Test
    @DisplayName("Deve buscar todos os client já cadastrados na base")
    fun `must search for all customers already registered in the database`() {
        val pagedRequest = PagedRequest(
            size = 20,
            offset = 0
        )

        val page: Page<ClientEntity> = PageImpl(listOf(fakeEntity))

        every { repository.findAll(pagedRequest.pageable()) } returns page
        every { mapper.entityToResponse(fakeEntity) } returns response

        val result = service.findAll(pagedRequest)

        verify(exactly = 1) { repository.findAll(pagedRequest.pageable()) }
        assertNotNull(result)
        assertEquals(result.totalPages, 1)
        assertEquals(result.totalElements, 1)
    }

    @Test
    @DisplayName("Edita o client")
    fun `edit the client`() {
        val entityEdit = ClientEntity(
            name = "PauloEdit",
            cpf = "222.222.222.22"
        )

        val response = ClientResponse(
            id = UUID.fromString("205847e8-7c73-4257-8d5f-9b2c61f9838b"),
            name = "PauloEdit",
            cpf = "222.222.222.22"
        )

        every { repository.findByCpf(any()) } returns fakeEntity
        every { repository.save(any()) } returns entityEdit
        every { mapper.entityToResponse(any()) } returns response

        val client = service.edit(request)

        verify(exactly = 1) { repository.findByCpf(any()) }
        verify(exactly = 1) { repository.save(any()) }
        verify(exactly = 1) { mapper.entityToResponse(any()) }
        assertEquals(entityEdit.cpf, client.cpf)
        assertEquals(entityEdit.name, client.name)
    }

    @Test
    @DisplayName("Deleta o client")
    fun `delete the client`() {
        every { repository.deleteById(any()) } just runs

        service.delete(fakeEntity.id!!)

        verify(exactly = 1) { repository.deleteById(any()) }
    }

}
