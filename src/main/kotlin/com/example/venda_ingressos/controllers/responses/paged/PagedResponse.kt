package com.example.venda_ingressos.controllers.responses.paged

import com.example.venda_ingressos.controllers.requests.paged.Paged

abstract class PagedResponse<T>(
    val data: List<T>,
    override val offset: Int,
    override val size: Int,
    val totalElements: Long?,
    val totalPages: Int?,
    val first: Boolean?,
    val last: Boolean?
) : Paged(offset, size)