package com.example.venda_ingressos.controller

import com.example.venda_ingressos.controller.request.RoomMovieRequest
import com.example.venda_ingressos.controller.request.paged.PagedRequest
import com.example.venda_ingressos.controller.response.RoomMovieResponse
import com.example.venda_ingressos.controller.response.paged.RoomMoviePagedResponse
import com.example.venda_ingressos.service.RoomMovieService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/room_movies")
class RoomMovieController(
    private val service: RoomMovieService
) {

    @PostMapping
    fun save(@RequestBody request: RoomMovieRequest): ResponseEntity<RoomMovieResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(service.save(request))
    }

    @GetMapping
    fun getAll(
        @RequestParam(defaultValue = "10") size: Int?,
        @RequestParam(defaultValue = "0") offset: Int?
    ): ResponseEntity<RoomMoviePagedResponse> {
        val pagedRequest = PagedRequest(
            size = size!!,
            offset = offset!!
        )

        val page = service.findAll(pagedRequest)

        return ResponseEntity.status(HttpStatus.OK).body(RoomMoviePagedResponse(page, offset))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID) {
        service.delete(id)
    }

}