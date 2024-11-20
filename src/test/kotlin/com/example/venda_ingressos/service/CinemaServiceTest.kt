package com.example.venda_ingressos.service

import com.example.venda_ingressos.controller.request.CinemaRequest
import com.example.venda_ingressos.controller.response.CinemaResponse
import com.example.venda_ingressos.entities.Cinema
import com.example.venda_ingressos.exceptions.EntityNotFoundException
import com.example.venda_ingressos.mapper.CinemaMapper
import com.example.venda_ingressos.repository.CinemaRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import com.example.venda_ingressos.exceptions.DataIntegrityViolationException
import java.util.*

@ExtendWith(MockKExtension::class)
class CinemaServiceTest {

    @MockK
    private lateinit var repository: CinemaRepository

    @MockK
    private lateinit var mapper: CinemaMapper

    @InjectMockKs
    private lateinit var service: CinemaService

    private lateinit var request: CinemaRequest
    private lateinit var response: CinemaResponse
    private lateinit var fakeEntity: Cinema

    @BeforeEach
    fun setUp() {
        request = CinemaRequest(phone = "01111111111", name = "Cinemax")
        fakeEntity = Cinema(
            id = UUID.fromString("205847e8-7c73-4257-8d5f-9b2c61f9838b"),
            phone = "01111111111",
            name = "Cinemax"
        )
        response = CinemaResponse(
            id = UUID.fromString("205847e8-7c73-4257-8d5f-9b2c61f9838b"),
            phone = "01111111111",
            name = "Cinemax"
        )
    }

    @Test
    @DisplayName("Efetua o cadastro do cinema")
    fun `register the cinema`() {
        every { mapper.requestToEntity(any()) } returns fakeEntity
        every { repository.save(any()) } returns fakeEntity
        every { mapper.entityToResponse(any()) } returns response

        val cinemaSaved = service.save(request)

        verify(exactly = 1) { repository.save(fakeEntity) }
        verify(exactly = 1) { mapper.requestToEntity(request) }
        verify(exactly = 1) { mapper.entityToResponse(fakeEntity) }

        assertEquals(response, cinemaSaved)
    }

    @Test
    @DisplayName("Busca o cinema baseado no ID passado")
    fun `search for the cinema based on the ID provided`() {
        every { repository.findById(any()).get() } returns fakeEntity

        val cinema = service.findById(fakeEntity.id!!)

        verify(exactly = 1) { repository.findById(any()) }
        assertEquals(fakeEntity, cinema)
    }

    @Test
    @DisplayName("Deleta o cinema")
    fun `delete the cinema`() {
        every { repository.deleteById(any()) } just runs

        service.delete(fakeEntity.id!!)

        verify(exactly = 1) { repository.deleteById(any()) }
    }

    @Test
    @DisplayName("Retorna uma exception ao salvar um numero repetido")
    fun `return an exception if save a repeated number`() {
        every { mapper.requestToEntity(any()) } returns fakeEntity
        every { repository.save(any()) } throws DataIntegrityViolationException(
            "m:save, msg:O telefone '${request.phone}' já existe na base."
        )

        val error = assertThrows<DataIntegrityViolationException> { service.save(request) }

        verify(exactly = 1) { repository.save(any()) }
        assertEquals("m:save, msg:O telefone '${request.phone}' já existe na base.", error.message)
    }

}
