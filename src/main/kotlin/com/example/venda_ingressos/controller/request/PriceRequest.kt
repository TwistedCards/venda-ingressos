package com.example.venda_ingressos.controller.request

import java.math.BigDecimal

class PriceRequest(
    var price: BigDecimal,
    var type: String,
    var quantityTickets: Int
)