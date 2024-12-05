package com.example.venda_ingressos.controllers

import com.example.venda_ingressos.controllers.requests.MovieRequest
import com.example.venda_ingressos.controllers.requests.paged.PagedRequest
import com.example.venda_ingressos.controllers.responses.MovieResponse
import com.example.venda_ingressos.controllers.responses.paged.MoviePagedResponse
import com.example.venda_ingressos.services.MovieService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/movies")
class MovieController(
    private val service: MovieService
) {

    @PostMapping
    fun save(@RequestBody request: MovieRequest): ResponseEntity<MovieResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request))
    }

    @GetMapping
    fun getAll(
        @RequestParam(defaultValue = "10") size: Int?,
        @RequestParam(defaultValue = "0") offset: Int?
    ): ResponseEntity<MoviePagedResponse> {
        val pagedRequest = PagedRequest(
            size = size!!,
            offset = offset!!
        )

        val page = service.findAll(pagedRequest)

        return ResponseEntity.status(HttpStatus.OK).body(MoviePagedResponse(page, offset))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: UUID) {
        service.delete(id)
    }

}