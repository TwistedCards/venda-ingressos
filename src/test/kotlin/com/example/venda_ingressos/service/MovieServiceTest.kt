package com.example.venda_ingressos.service

import com.example.venda_ingressos.controller.request.MovieRequest
import com.example.venda_ingressos.controller.response.MovieResponse
import com.example.venda_ingressos.entities.Movie
import com.example.venda_ingressos.mapper.MovieMapper
import com.example.venda_ingressos.repository.MovieRepository
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
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDate
import java.util.*

@ExtendWith(MockKExtension::class)
class MovieServiceTest {

    @MockK
    private lateinit var repository: MovieRepository

    @MockK
    private lateinit var mapper: MovieMapper

    @InjectMockKs
    private lateinit var service: MovieService

    private lateinit var request: MovieRequest
    private lateinit var response: MovieResponse
    private lateinit var fakeEntity: Movie

    @BeforeEach
    fun setUp() {
        request = MovieRequest(
            title = "A caveira que sangrava",
            originalTitle = "The bleeding skull",
            indicativeClassification = "18",
            releaseDate = LocalDate.now(),
            synopsis = "bla bla bla"
        )

        fakeEntity = Movie(
            id = UUID.fromString("205847e8-7c73-4257-8d5f-9b2c61f9838b"),
            title = "A caveira que sangrava",
            originalTitle = "The bleeding skull",
            indicativeClassification = "18",
            releaseDate = LocalDate.now(),
            synopsis = "bla bla bla"
        )

        response = MovieResponse(
            title = "A caveira que sangrava",
            originalTitle = "The bleeding skull",
            indicativeClassification = "18",
            releaseDate = LocalDate.now(),
            synopsis = "bla bla bla"
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
    @DisplayName("Efetua o cadastro do filme com os assentos e sessões")
    fun `register the cinema with the seats and sessions`() {
    }

    @Test
    @DisplayName("Busca o filme baseado no ID passado")
    fun `search for the cinema based on the ID provided`() {
        every { repository.findById(any()).get() } returns fakeEntity

        val movie = service.findById(fakeEntity.id!!)

        verify(exactly = 1) { repository.findById(any()) }
        assertEquals(fakeEntity, movie)
    }

    @Test
    @DisplayName("Busca todos os filmes já cadastrados na base")
    fun `find all movies registered in the database`() {
    }

    @Test
    @DisplayName("Deleta o filme")
    fun `delete the movie`() {
        every { repository.deleteById(any()) } just runs

        service.delete(fakeEntity.id!!)

        verify(exactly = 1) { repository.deleteById(any()) }
    }

}
