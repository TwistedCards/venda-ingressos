package com.example.venda_ingressos.controller

import com.example.venda_ingressos.controller.request.paged.PagedRequest
import com.example.venda_ingressos.controller.request.SessionRequest
import com.example.venda_ingressos.controller.response.paged.SessionPagedResponse
import com.example.venda_ingressos.controller.response.SessionResponse
import com.example.venda_ingressos.service.SessionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sessions")
class SessionController(
    private val service: SessionService
) {

    @PostMapping
    fun save(@RequestBody request: SessionRequest): ResponseEntity<SessionResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request))
    }

    @GetMapping
    fun getAll(
        @RequestParam(defaultValue = "10") size: Int?,
        @RequestParam(defaultValue = "0") offset: Int?
    ): ResponseEntity<SessionPagedResponse> {
        val pagedRequest = PagedRequest(
            size = size!!,
            offset = offset!!
        )

        val page = service.findAll(pagedRequest)

        return ResponseEntity.status(HttpStatus.OK).body(SessionPagedResponse(page, offset))
    }

}