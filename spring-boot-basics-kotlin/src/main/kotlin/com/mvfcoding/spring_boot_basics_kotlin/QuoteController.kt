package com.mvfcoding.spring_boot_basics_kotlin

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException


@RestController
@RequestMapping("/quotes")
class QuoteController {

    val quotes = mutableListOf<QuoteDto>()

    @GetMapping
    fun loadQuotes(
        @RequestParam("q", required = false) query: String?,
    ): List<QuoteDto> {
        return if(query != null) {
            quotes.filter { it.content.contains(query, ignoreCase = true) }
        } else quotes
    }

    @PostMapping
    fun postQuotes(
        @RequestBody quoteDto: QuoteDto
    ): QuoteDto {
        quotes.add(quoteDto)
        return quoteDto
    }

    @PutMapping
    fun putQuotes(
        @RequestBody quoteDto: QuoteDto
    ): QuoteDto {
        val index = quotes.indexOfFirst { it.id == quoteDto.id }
        quotes[index] = quoteDto
        return quoteDto
    }

    @DeleteMapping("{id}")
    fun deleteQuotesById(@PathVariable id: Long) {
        val quoteToDelete = quotes.find { it.id == id }
        if (quoteToDelete != null) {
            quotes.remove(quoteToDelete)
        } else {
            throw QuoteNotFoundException(id)
        }
    }
}