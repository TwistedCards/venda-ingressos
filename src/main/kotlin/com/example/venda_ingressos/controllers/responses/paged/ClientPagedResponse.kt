package com.example.venda_ingressos.controllers.responses.paged

import com.example.venda_ingressos.controllers.responses.ClientResponse
import org.springframework.data.domain.Page

class ClientPagedResponse(
    data: List<ClientResponse>,
    offset: Int,
    size: Int,
    totalElements: Long,
    totalPages: Int,
    first: Boolean,
    last: Boolean
) : PagedResponse<ClientResponse>(
    data, offset, size, totalElements, totalPages, first, last
) {
    constructor(page: Page<ClientResponse>, offset: Int) : this(
        data = page.content,
        offset = offset,
        size = page.size,
        totalElements = page.totalElements,
        totalPages = page.totalPages,
        first = page.isFirst,
        last = page.isLast
    )
}