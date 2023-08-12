package com.ivand.LinkShortener.model

import com.github.springtestdbunit.DbUnitTestExecutionListener
import com.ivand.LinkShortener.LinkShortenerApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests

@TestExecutionListeners(DbUnitTestExecutionListener::class)
@SpringBootTest(classes = arrayOf(LinkShortenerApplication::class))
@TestPropertySource(locations = arrayOf("classpath:repositories-test.properties"))
@DirtiesContext
abstract class AbstractRepositoryTest: AbstractTransactionalJUnit4SpringContextTests() {

}