package com.example.venda_ingressos.controller.response.paged

import com.example.venda_ingressos.controller.response.SessionResponse
import org.springframework.data.domain.Page

class SessionPagedResponse(
    data: List<SessionResponse>,
    offset: Int,
    size: Int,
    totalElements: Long,
    totalPages: Int,
    first: Boolean,
    last: Boolean
) : PagedResponse<SessionResponse>(
    data, offset, size, totalElements, totalPages, first, last
) {
    constructor(page: Page<SessionResponse>, offset: Int) : this(
        data = page.content,
        offset = offset,
        size = page.size,
        totalElements = page.totalElements,
        totalPages = page.totalPages,
        first = page.isFirst,
        last = page.isLast
    )
}