package com.example.venda_ingressos.controller.request.paged

class PagedRequest (
    size: Int,
    offset: Int
) : Paged(offset, size)