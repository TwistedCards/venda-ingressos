package com.example.venda_ingressos.service

import com.example.venda_ingressos.controller.request.MovieRequest
import com.example.venda_ingressos.controller.response.MovieResponse
import com.example.venda_ingressos.entities.CategoryEnum
import com.example.venda_ingressos.entities.Movie
import com.example.venda_ingressos.entities.Seat
import com.example.venda_ingressos.exceptions.DataIntegrityViolationException
import com.example.venda_ingressos.exceptions.EntityNotFoundException
import com.example.venda_ingressos.mapper.MovieMapper
import com.example.venda_ingressos.repository.MovieRepository
import com.example.venda_ingressos.repository.SeatRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDate
import java.util.*

@ExtendWith(MockKExtension::class)
class MovieServiceTest {

    @MockK
    private lateinit var repository: MovieRepository

    @MockK
    private lateinit var seatRepository: SeatRepository

    @MockK
    private lateinit var mapper: MovieMapper

    @InjectMockKs
    private lateinit var service: MovieService

    private lateinit var request: MovieRequest
    private lateinit var response: MovieResponse
    private lateinit var fakeEntity: Movie
    private lateinit var fakeSeatEntity: Seat

    @BeforeEach
    fun setUp() {
        request = MovieRequest(
            title = "A caveira que sangrava",
            originalTitle = "The bleeding skull",
            indicativeClassification = "18",
            releaseDate = LocalDate.now(),
            synopsis = "bla bla bla",
            duration = "1h30m"
        )

        fakeEntity = Movie(
            id = UUID.fromString("205847e8-7c73-4257-8d5f-9b2c61f9838b"),
            title = "A caveira que sangrava",
            originalTitle = "The bleeding skull",
            indicativeClassification = "18",
            releaseDate = LocalDate.now(),
            synopsis = "bla bla bla",
            duration = "1h30m"
        )

        fakeSeatEntity = Seat(
            id = UUID.fromString("195f471e-0234-446f-be1f-014b77809703"),
            codSeat = "3B",
            category = CategoryEnum.NORMAL,
            movie = fakeEntity
        )

        response = MovieResponse(
            title = "A caveira que sangrava",
            originalTitle = "The bleeding skull",
            indicativeClassification = "18",
            releaseDate = LocalDate.now(),
            synopsis = "bla bla bla",
            duration = "1h30m"
        )
    }

    @Test
    @DisplayName("Efetua o cadastro do filme")
    fun `register the movie`() {
        every { mapper.requestToEntity(any()) } returns fakeEntity
        every { repository.save(any()) } returns fakeEntity
        every { mapper.entityToResponse(any()) } returns response

        val movieSaved = service.save(request)

        verify(exactly = 1) { repository.save(fakeEntity) }
        verify(exactly = 1) { mapper.requestToEntity(request) }
        verify(exactly = 1) { mapper.entityToResponse(fakeEntity) }

        assertEquals(response, movieSaved)
    }

    @Test
    @DisplayName("Busca o filme baseado no ID passado")
    fun `search for the cinema based on the ID provided`() {
        every { repository.findById(any()) } returns Optional.of(fakeEntity)

        val movie = assertDoesNotThrow{ service.findById(fakeEntity.id!!) }

        verify(exactly = 1) { repository.findById(any()) }
        assertEquals(fakeEntity, movie)
    }

    @Test
    @DisplayName("Busca todos os filmes já cadastrados na base")
    fun `find all movies registered in the database`() {
    }

    @Test
    @DisplayName("Retorna uma exception ao buscar um filme com um id que não existe")
    fun `returns an exception when searching for a movie with an id that does not exist`() {
        val id = UUID.randomUUID()

        every { repository.findById(id) } returns Optional.empty()

        val error = assertThrows<EntityNotFoundException> { service.findById(id) }

        verify(exactly = 1) { repository.findById(any()) }
        assertEquals("O filme com id $id não foi encontrado", error.message)
    }

    @Test
    @DisplayName("Deleta o filme")
    fun `delete the movie`() {
        every { repository.deleteById(any()) } just runs

        service.delete(fakeEntity.id!!)

        verify(exactly = 1) { repository.deleteById(any()) }
    }

    @Test
    @DisplayName("Retorna uma exception ao salvar um filme repetido")
    fun `return an exception if save a repeated movie`() {
        every { mapper.requestToEntity(any()) } returns fakeEntity
        every { repository.save(any()) } throws DataIntegrityViolationException(
            "m:save, msg:O titulo '${request.title}' já existe na base."
        )

        val error = assertThrows<DataIntegrityViolationException> { service.save(request) }

        verify(exactly = 1) { repository.save(any()) }
        assertEquals("m:save, msg:O titulo '${request.title}' já existe na base.", error.message)
    }

}
