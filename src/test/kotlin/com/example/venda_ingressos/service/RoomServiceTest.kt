package com.example.venda_ingressos.service

import com.example.venda_ingressos.controller.request.RoomRequest
import com.example.venda_ingressos.controller.response.RoomResponse
import com.example.venda_ingressos.entities.Cinema
import com.example.venda_ingressos.entities.Room
import com.example.venda_ingressos.mapper.RoomMapper
import com.example.venda_ingressos.repository.CinemaRepository
import com.example.venda_ingressos.repository.RoomRepository
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
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

@ExtendWith(MockKExtension::class)
class RoomServiceTest {

    @MockK
    private lateinit var repository: RoomRepository

    @MockK
    private lateinit var cinemaRepository: CinemaRepository

    @MockK
    private lateinit var mapper: RoomMapper

    @InjectMockKs
    private lateinit var service: RoomService

    private lateinit var request: RoomRequest
    private lateinit var response: RoomResponse
    private lateinit var fakeEntity: Room
    private lateinit var fakeCinema: Cinema

    @BeforeEach
    fun setUp() {
        fakeCinema = Cinema(
            id = UUID.fromString("486db27f-de44-4e73-8511-ab656dba1cce"),
            phone = "01111111111",
            name = "Cinemax"
        )

        request = RoomRequest(
            totalCapacity = 100,
            roomName = "5B",
            idCinema = UUID.fromString("486db27f-de44-4e73-8511-ab656dba1cce")
        )

        fakeEntity = Room(
            id = UUID.fromString("205847e8-7c73-4257-8d5f-9b2c61f9838b"),
            totalCapacity = 100,
            roomName = "5B",
            cinema = fakeCinema
        )

        response = RoomResponse(
            id = UUID.fromString("205847e8-7c73-4257-8d5f-9b2c61f9838b"),
            totalCapacity = 100,
            roomName = "5B"
        )
    }

    @Test
    @DisplayName("Efetua o cadastro da sala")
    fun `register the room`() {
        every { cinemaRepository.findById(any()) } returns Optional.of(fakeCinema)

        every { mapper.requestToEntity(any(), any()) } returns fakeEntity
        every { repository.save(any()) } returns fakeEntity
        every { mapper.entityToResponse(any()) } returns response

        val roomSaved = assertDoesNotThrow { service.save(request) }

        verify(exactly = 1) { cinemaRepository.findById(fakeCinema.id!!) }
        verify(exactly = 1) { repository.save(fakeEntity) }
        verify(exactly = 1) { mapper.requestToEntity(request, fakeCinema) }
        verify(exactly = 1) { mapper.entityToResponse(fakeEntity) }

        assertEquals(response, roomSaved)
    }

    @Test
    @DisplayName("Busca a sala baseada no ID passado")
    fun `search for the room based on the ID provided`() {
        every { repository.findById(any()).get() } returns fakeEntity

        val room = service.findById(fakeEntity.id!!)

        verify(exactly = 1) { repository.findById(any()) }
        assertEquals(fakeEntity, room)
    }

    @Test
    @DisplayName("Busca todas as salas já cadastradas na base")
    fun `find all rooms registered in the database`() {
    }

    @Test
    @DisplayName("Deleta a sala")
    fun `delete the movie`() {
        every { repository.deleteById(any()) } just runs

        service.delete(fakeEntity.id!!)

        verify(exactly = 1) { repository.deleteById(any()) }
    }

}
