package com.ivand.LinkShortener.controllers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ivand.LinkShortener.LinkShortenerApplication
import com.ivand.LinkShortener.model.Link
import com.ivand.LinkShortener.repository.LinkRepository
import com.ivand.LinkShortener.services.KeyMapperService
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import whenever
import java.util.*

@ExtendWith(SpringExtension::class)
@TestPropertySource(locations = arrayOf("classpath:repositories-test.properties"))
@SpringBootTest(classes = arrayOf(LinkShortenerApplication::class))
@WebAppConfiguration
class AddControllerTest {


    private val KEY = "key"
    private val LINK = "link"
    private val LINK_A = "abc"
    private val LINK_B = "cde"
    private val ID_A = 10000000L
    private val ID_B = 10000001L
    private val LINK_OBJ_B: Link = Link(LINK_B,ID_B)
    private val LINK_OBJ_A: Link = Link(LINK_A,ID_A)

    @Autowired
    lateinit var webApplicationContext: WebApplicationContext

    lateinit var mockMvc: MockMvc

    @Mock
    lateinit var service: KeyMapperService

    @Autowired
    @InjectMocks
    lateinit var controller: AddController

    @Mock
    lateinit var repo: LinkRepository
    @BeforeEach
    fun init(){
        MockitoAnnotations.initMocks(this)
        mockMvc = MockMvcBuilders
            .webAppContextSetup(webApplicationContext).build()
        whenever(service.add(LINK)).thenReturn(KEY)
        whenever(repo.findById(Mockito.anyLong())).thenReturn(Optional.empty())
        whenever(repo.save(Link(LINK_A))).thenReturn(LINK_OBJ_A)
        whenever(repo.save(Link(LINK_B))).thenReturn(LINK_OBJ_B)
        whenever(repo.findById(ID_A)).thenReturn(Optional.of(LINK_OBJ_A))
        whenever(repo.findById(ID_B)).thenReturn(Optional.of(LINK_OBJ_B))
    }

    @Test
    fun whenUserAddsLinkHeTakesAKey(){
        mockMvc.perform(post("/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jacksonObjectMapper().writeValueAsString(AddController.AddRequest(LINK))))
            .andExpect(jsonPath("$.key", Matchers.equalTo(KEY)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.link",Matchers.equalTo(LINK)))
    }

    @Test
    fun whenUserAddLinkToFormHeRedirectsToResult(){
        mockMvc.perform(post("/addhtml")
            .param("link", LINK).contentType(MediaType.APPLICATION_FORM_URLENCODED))
            .andExpect(status().isOk).andExpect(content().string(Matchers.containsString(KEY)))
            .andExpect(status().isOk).andExpect(content().string(Matchers.containsString(LINK)))
    }
}