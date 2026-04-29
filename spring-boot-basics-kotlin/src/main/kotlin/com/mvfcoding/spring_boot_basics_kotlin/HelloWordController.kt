package com.mvfcoding.spring_boot_basics_kotlin

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWordController {

    @GetMapping
    fun HelloWord() = "Hello World"
}