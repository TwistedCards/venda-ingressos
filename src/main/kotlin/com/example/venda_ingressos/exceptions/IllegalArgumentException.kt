package com.example.venda_ingressos.exceptions

open class IllegalArgumentException(message: String? = null, cause: Throwable? = null) : BusinessException(message, cause){

    constructor(newMessage: String): this(
        message = newMessage
    )

}