package com.ivand.LinkShortener.controllers

import com.ivand.LinkShortener.LinkShortenerApplication
import com.ivand.LinkShortener.services.KeyMapperService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext


@ExtendWith(SpringExtension::class)
@TestPropertySource(locations = arrayOf("classpath:repositories-test.properties"))
@SpringBootTest(classes = arrayOf(LinkShortenerApplication::class))
@WebAppConfiguration
class RedirectControllerTest {

    private val PATH = "aAbBcCdD"
    private val REDIRECT_STATUS: Int = 302
    private val HEADER_NAME = "Location"
    private val HEADER_VALUE = "https://vk.com/im"
    private val BAD_PATH = "olololo"
    private val NOT_FOUND:Int = 404

    @Autowired
    lateinit var webApplicationContext: WebApplicationContext

    lateinit var mockMvc: MockMvc

    @Mock
    lateinit var service: KeyMapperService

    @Autowired
    @InjectMocks
    lateinit var controller: RedirectController

    @BeforeEach
    fun init(){
        MockitoAnnotations.initMocks(this)
        mockMvc = MockMvcBuilders
            .webAppContextSetup(webApplicationContext).build()

        Mockito.`when`(service.getLink(PATH)).thenReturn(KeyMapperService.Get.Link(HEADER_VALUE))
        Mockito.`when`(service.getLink(BAD_PATH)).thenReturn(KeyMapperService.Get.NotFound(BAD_PATH))
    }

    @Test
    fun controllerMustRedirectWhenRequestIsSuccessful(){
        mockMvc.perform(get("/"+PATH))
            .andExpect(status().`is`(REDIRECT_STATUS))
            .andExpect(header().string(HEADER_NAME,HEADER_VALUE))
    }

    @Test
    fun controllerMustReturn404IfBadKey(){
        mockMvc.perform(get("/"+BAD_PATH))
            .andExpect(status().`is`(NOT_FOUND))
    }

    @Test
    fun homeWorksFine(){
        mockMvc.perform(get("/"))
            .andExpect(MockMvcResultMatchers.view().name("home"))
    }

}