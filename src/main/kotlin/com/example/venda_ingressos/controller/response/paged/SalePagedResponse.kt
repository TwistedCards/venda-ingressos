package com.example.venda_ingressos.controller.response.paged

import org.springframework.data.domain.Page

class SalePagedResponse(
//    data: List<SaleResponse>,
    offset: Int,
    size: Int,
    totalElements: Long,
    totalPages: Int,
    first: Boolean,
    last: Boolean
)
//) : PagedResponse<SaleResponse>(
//    data, offset, size, totalElements, totalPages, first, last
//) {
//    constructor(page: Page<SaleResponse>, offset: Int) : this(
//        data = page.content,
//        offset = offset,
//        size = page.size,
//        totalElements = page.totalElements,
//        totalPages = page.totalPages,
//        first = page.isFirst,
//        last = page.isLast
//    )
//}