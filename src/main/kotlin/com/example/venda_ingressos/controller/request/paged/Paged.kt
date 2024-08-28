package com.example.venda_ingressos.controller.request.paged

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

abstract class Paged(
    open val offset: Int,
    open val size: Int
) {
    fun pageable(): Pageable = PageRequest.of(offset, size)
}