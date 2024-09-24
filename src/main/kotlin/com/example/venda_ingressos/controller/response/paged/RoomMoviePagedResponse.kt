package com.example.venda_ingressos.controller.response.paged

import com.example.venda_ingressos.controller.response.CinemaResponse
import com.example.venda_ingressos.controller.response.RoomMovieResponse
import com.example.venda_ingressos.controller.response.RoomResponse
import org.springframework.data.domain.Page

class RoomMoviePagedResponse(
    data: List<RoomMovieResponse>,
    offset: Int,
    size: Int,
    totalElements: Long,
    totalPages: Int,
    first: Boolean,
    last: Boolean
) : PagedResponse<RoomMovieResponse>(
    data, offset, size, totalElements, totalPages, first, last
) {
    constructor(page: Page<RoomMovieResponse>, offset: Int) : this(
        data = page.content,
        offset = offset,
        size = page.size,
        totalElements = page.totalElements,
        totalPages = page.totalPages,
        first = page.isFirst,
        last = page.isLast
    )
}