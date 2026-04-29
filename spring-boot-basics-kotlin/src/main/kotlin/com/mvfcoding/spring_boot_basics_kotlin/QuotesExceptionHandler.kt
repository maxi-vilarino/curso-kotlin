package com.mvfcoding.spring_boot_basics_kotlin

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class QuotesExceptionHandler {

    @ExceptionHandler(QuoteNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun onQuoteNotFound(e: QuoteNotFoundException) = mapOf(
        "errorCode" to "QUOTE_NOT_FOUND",
        "message" to e.message
    )
}