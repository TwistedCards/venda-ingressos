package com.example.venda_ingressos.controller.response

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

data class ErrorResponse(
    var status: Int,
    var error: String,
    var message: String,
    var timesTamp: LocalDateTime
) {

    constructor(status: HttpStatus, ex: Exception, message: String? = null) : this (
        status = status.value(),
        error = status.name,
        message = message ?: ex.message ?: String(),
        timesTamp = LocalDateTime.now()
    )

}