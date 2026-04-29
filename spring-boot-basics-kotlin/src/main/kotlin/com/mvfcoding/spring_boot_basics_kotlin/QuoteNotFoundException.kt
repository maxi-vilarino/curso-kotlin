package com.mvfcoding.spring_boot_basics_kotlin

class QuoteNotFoundException(
    private val id: Long
): RuntimeException("A quote with id $id was not found.") {

}