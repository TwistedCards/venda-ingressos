package com.example.venda_ingressos.controllers.requests.paged

class PagedRequest (
    size: Int,
    offset: Int
) : Paged(offset, size)