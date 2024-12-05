package com.example.venda_ingressos.controllers.responses.paged

import com.example.venda_ingressos.controllers.responses.MovieResponse
import org.springframework.data.domain.Page

class MoviePagedResponse(
    data: List<MovieResponse>,
    offset: Int,
    size: Int,
    totalElements: Long,
    totalPages: Int,
    first: Boolean,
    last: Boolean
) : PagedResponse<MovieResponse>(
    data, offset, size, totalElements, totalPages, first, last
) {
    constructor(page: Page<MovieResponse>, offset: Int) : this(
        data = page.content,
        offset = offset,
        size = page.size,
        totalElements = page.totalElements,
        totalPages = page.totalPages,
        first = page.isFirst,
        last = page.isLast
    )
}