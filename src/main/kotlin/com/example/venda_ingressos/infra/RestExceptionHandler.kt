package com.example.venda_ingressos.infra

import com.example.venda_ingressos.controller.response.ErrorResponse
import com.example.venda_ingressos.exceptions.EntityNotFoundException
import com.example.venda_ingressos.exceptions.IllegalArgumentException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime

@RestControllerAdvice
class RestExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(EntityNotFoundException::class)
    fun eventNotFoundHandler(ex: EntityNotFoundException): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.name,
            message = ex.message!!,
            timesTamp = LocalDateTime.now()
        )

        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun eventNotFoundHandler(ex: IllegalArgumentException): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.name,
            message = ex.message!!,
            timesTamp = LocalDateTime.now()
        )

        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }

}