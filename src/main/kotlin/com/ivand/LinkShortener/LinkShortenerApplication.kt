package com.ivand.LinkShortener

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LinkShortenerApplication

fun main(args: Array<String>) {
	runApplication<LinkShortenerApplication>(*args)
}
