package com.example.venda_ingressos.service

import com.example.venda_ingressos.controller.request.SessionRequest
import com.example.venda_ingressos.controller.response.SessionResponse
import com.example.venda_ingressos.entities.CinemaEntity
import com.example.venda_ingressos.entities.MovieEntity
import com.example.venda_ingressos.entities.RoomEntity
import com.example.venda_ingressos.entities.SessionEntity
import com.example.venda_ingressos.exceptions.EntityNotFoundException
import com.example.venda_ingressos.exceptions.IllegalArgumentException
import com.example.venda_ingressos.mapper.SessionMapper
import com.example.venda_ingressos.repository.MovieRepository
import com.example.venda_ingressos.repository.RoomRepository
import com.example.venda_ingressos.repository.SessionRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@ExtendWith(MockKExtension::class)
class SessionServiceTest {

    @MockK
    private lateinit var repository: SessionRepository

    @MockK
    private lateinit var movieRepository: MovieRepository

    @MockK
    private lateinit var roomRepository: RoomRepository

    @MockK
    private lateinit var mapper: SessionMapper

    @InjectMockKs
    private lateinit var service: SessionService

    private lateinit var request: SessionRequest
    private lateinit var response: SessionResponse
    private lateinit var fakeEntity: SessionEntity
    private lateinit var movieEntity: MovieEntity
    private lateinit var roomEntity: RoomEntity

    @BeforeEach
    fun setUp() {
        movieEntity = MovieEntity(
            id = UUID.fromString("ef69acc2-aa99-4639-a6f9-d4ecf1b320be"),
            title = "A caveira que sangrava",
            originalTitle = "The bleeding skull",
            indicativeClassification = "18",
            releaseDate = LocalDate.now(),
            synopsis = "bla bla bla",
            duration = "1h30m"
        )

        roomEntity = RoomEntity(
            id = UUID.fromString("35169367-fdfc-4eb8-8475-4d34e29869ab"),
            totalCapacity = 100,
            roomName = "5B",
            cinema = CinemaEntity(
                id = UUID.fromString("9d216a65-553a-44f5-924d-d8df1f281978"),
                phone = "01111111111",
                name = "Cinemax"
            )
        )

        request = SessionRequest(
            startTime = "2019-03-27T10:15:30",
            idMovie = UUID.fromString("ef69acc2-aa99-4639-a6f9-d4ecf1b320be"),
            idRoom = UUID.fromString("35169367-fdfc-4eb8-8475-4d34e29869ab")
        )

        fakeEntity = SessionEntity(
            id = UUID.fromString("205847e8-7c73-4257-8d5f-9b2c61f9838b"),
            startTime = LocalDateTime.parse("2019-03-27T10:15:30"),
            movie = movieEntity,
            room = roomEntity
        )

        response = SessionResponse(
            startTime = LocalDateTime.now(),
            movieName = "A caveira que sangrava",
            roomName = "5B"
        )
    }

    @Test
    @DisplayName("Salva a sessão")
    fun `save the session`() {
        every { movieRepository.findById(any()) } returns Optional.of(movieEntity)
        every { roomRepository.findById(any()) } returns Optional.of(roomEntity)
        every { mapper.requestToEntity(any(), any(), any()) } returns fakeEntity
        every { repository.findByMovieId(any()) } returns null
        every { repository.save(any()) } returns fakeEntity
        every { mapper.entityToResponse(any()) } returns response

        val sessionSaved = service.save(request)

        verify(exactly = 1) { repository.save(fakeEntity) }
        verify(exactly = 1) { movieRepository.findById(movieEntity.id!!) }
        verify(exactly = 1) { roomRepository.findById(roomEntity.id!!) }
        verify(exactly = 1) { mapper.requestToEntity(any(), any(), any()) }
        verify(exactly = 1) { mapper.entityToResponse(any()) }

        assertEquals(response, sessionSaved)
    }

    @Test
    @DisplayName("Retorna uma exceção ao não encontrar o filme pelo id")
    fun `returns an exception when not finding the movie by id`() {
        every { repository.findByMovieId(any()) } returns null
        every { movieRepository.findById(any()) } throws EntityNotFoundException(
            "O filme com id ${request.idMovie} não foi encontrado."
        )

        val error = assertThrows<EntityNotFoundException> { service.save(request) }

        verify(exactly = 1) { movieRepository.findById(any()) }
        assertEquals("O filme com id ${request.idMovie} não foi encontrado.", error.message)
    }

    @Test
    @DisplayName("Retorna uma exceção ao não encontrar a sala pelo id")
    fun `returns an exception when not finding the room by id`() {
        every { repository.findByMovieId(any()) } returns null
        every { movieRepository.findById(any()) } returns Optional.of(movieEntity)
        every { roomRepository.findById(any()) } throws EntityNotFoundException(
            "A sala com id ${request.idRoom} não foi encontrada."
        )

        val error = assertThrows<EntityNotFoundException> { service.save(request) }

        verify(exactly = 1) { roomRepository.findById(any()) }
        assertEquals("A sala com id ${request.idRoom} não foi encontrada.", error.message)
    }

    @Test
    @DisplayName("Retorna uma exceção ao salvar uma sessão com horário e sala repetidos")
    fun `returns an exception when saving a session with a repeated time and room`() {
        every { repository.findByMovieId(any()) } returns mutableListOf(fakeEntity)

        val error = assertThrows<IllegalArgumentException> { service.save(request) }

        verify(exactly = 1) { repository.findByMovieId(any()) }
        assertEquals(
            "m=save, msg=Já existe uma sessão salva na data e hora '${request.startTime}' " +
                    "na sala '${roomEntity.roomName}'", error.message
        )
    }

}