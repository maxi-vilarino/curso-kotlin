package com.mvfcoding.spring_boot_basics_kotlin

import com.mvfcoding.spring_boot_basics_kotlin.config.QuotesConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(QuotesConfig::class)
class SpringBootBasicsKotlinApplication

fun main(args: Array<String>) {
	runApplication<SpringBootBasicsKotlinApplication>(*args)
}
