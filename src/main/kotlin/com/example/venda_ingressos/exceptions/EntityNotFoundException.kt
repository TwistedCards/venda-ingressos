package com.example.venda_ingressos.exceptions

open class EntityNotFoundException(message: String? = null, cause: Throwable? = null) : BusinessException(message, cause){

    constructor(newMessage: String): this(
        message = newMessage
    )

}