package com.mvfcoding.spring_boot_basics_kotlin.repository

import com.mvfcoding.spring_boot_basics_kotlin.QuoteDto
import com.mvfcoding.spring_boot_basics_kotlin.QuoteNotFoundException
import org.springframework.stereotype.Repository

@Repository
class QuotesRepository {

    private val quotes = mutableListOf<QuoteDto>()

    fun getQuotes(query: String?): List<QuoteDto> {
        return if(query != null) {
            quotes.filter { it.content.contains(query, ignoreCase = true) }
        } else quotes
    }

    fun insertQuote( quoteDto: QuoteDto): QuoteDto {
        quotes.add(quoteDto)
        return quoteDto
    }

    fun updateQuote( quoteDto: QuoteDto): QuoteDto {
        val index = quotes.indexOfFirst { it.id == quoteDto.id }
        quotes[index] = quoteDto
        return quoteDto
    }

    fun deleteQuote(quoteId: Long): Boolean {
        val quoteToDelete = quotes.find { it.id == quoteId }
        return if (quoteToDelete != null) {
            quotes.remove(quoteToDelete)
            true
        } else {
            false
        }
    }
}