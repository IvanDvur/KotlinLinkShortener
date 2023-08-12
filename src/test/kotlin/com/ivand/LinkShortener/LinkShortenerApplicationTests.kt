package com.ivand.LinkShortener

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.web.WebAppConfiguration

@SpringBootTest
@TestPropertySource(locations = arrayOf("classpath:repositories-test.properties"))
class LinkShortenerApplicationTests {

	@Test
	fun contextLoads() {
	}

}
