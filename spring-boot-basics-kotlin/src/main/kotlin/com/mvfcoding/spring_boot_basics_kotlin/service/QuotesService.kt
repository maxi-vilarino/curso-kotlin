package com.mvfcoding.spring_boot_basics_kotlin.service

import com.mvfcoding.spring_boot_basics_kotlin.QuoteDto
import com.mvfcoding.spring_boot_basics_kotlin.QuoteNotFoundException
import com.mvfcoding.spring_boot_basics_kotlin.repository.QuotesRepository
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class QuotesService(
    private val quotesRepository: QuotesRepository,
    private val restTemplate: RestTemplate
) {
    fun getQuotes(query: String?): List<QuoteDto> {
        return quotesRepository.getQuotes(query)
    }

    fun insertQuote(quote: QuoteDto): QuoteDto {
        return quotesRepository.insertQuote(quote)
    }

    fun updateQuote(quote: QuoteDto): QuoteDto {
        return quotesRepository.updateQuote(quote)
    }

    fun deleteQuote(quoteId: Long){
        if(!quotesRepository.deleteQuote(quoteId)){
            throw QuoteNotFoundException(quoteId)
        }
    }
}