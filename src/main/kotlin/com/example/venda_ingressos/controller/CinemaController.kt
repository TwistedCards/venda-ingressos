package com.example.venda_ingressos.controller

import com.example.venda_ingressos.controller.request.CinemaRequest
import com.example.venda_ingressos.controller.request.ClientRequest
import com.example.venda_ingressos.controller.request.paged.PagedRequest
import com.example.venda_ingressos.controller.response.CinemaResponse
import com.example.venda_ingressos.controller.response.ClientResponse
import com.example.venda_ingressos.controller.response.paged.CinemaPagedResponse
import com.example.venda_ingressos.controller.response.paged.ClientPagedResponse
import com.example.venda_ingressos.service.CinemaService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/cinemas")
class CinemaController(
    private val service: CinemaService
) {

    @PostMapping
    fun save(@RequestBody request: CinemaRequest): ResponseEntity<CinemaResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(service.save(request))
    }

    @GetMapping
    fun getAll(
        @RequestParam(defaultValue = "10") size: Int?,
        @RequestParam(defaultValue = "0") offset: Int?
    ): ResponseEntity<CinemaPagedResponse> {
        val pagedRequest = PagedRequest(
            size = size!!,
            offset = offset!!
        )

        val page = service.findAll(pagedRequest)

        return ResponseEntity.status(HttpStatus.OK).body(CinemaPagedResponse(page, offset))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID) {
        service.delete(id)
    }

}