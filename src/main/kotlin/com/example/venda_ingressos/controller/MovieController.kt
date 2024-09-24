package com.example.venda_ingressos.controller

import com.example.venda_ingressos.controller.request.MovieRequest
import com.example.venda_ingressos.controller.request.RoomRequest
import com.example.venda_ingressos.controller.request.paged.PagedRequest
import com.example.venda_ingressos.controller.response.MovieResponse
import com.example.venda_ingressos.controller.response.RoomResponse
import com.example.venda_ingressos.controller.response.paged.CinemaPagedResponse
import com.example.venda_ingressos.controller.response.paged.MoviePagedResponse
import com.example.venda_ingressos.controller.response.paged.RoomPagedResponse
import com.example.venda_ingressos.service.MovieService
import com.example.venda_ingressos.service.RoomService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/rooms")
class MovieController(
    private val service: MovieService
) {

    @PostMapping
    fun save(@RequestBody request: MovieRequest): ResponseEntity<MovieResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(service.save(request))
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
    fun delete(@PathVariable id: UUID) {
        service.delete(id)
    }

}