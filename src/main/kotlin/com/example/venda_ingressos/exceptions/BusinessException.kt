package com.example.venda_ingressos.exceptions

open class BusinessException(message: String?, cause: Throwable?) : RuntimeException(message, cause)