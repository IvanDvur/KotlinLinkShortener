package com.ivand.LinkShortener.service

import com.ivand.LinkShortener.services.DefaultKeyMapperService
import com.ivand.LinkShortener.services.KeyConverterService
import com.ivand.LinkShortener.services.KeyMapperService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.concurrent.atomic.AtomicLong

class DefaultKeyMapperServiceTest {
    private val KEY_A: String = "abc"
    private val KEY_B: String = "cde"
    private val ID_A: Long = 10000000L
    private val ID_B: Long = 10000001L

    @InjectMocks
    val service: KeyMapperService = DefaultKeyMapperService()
    private val KEY: String = "aAbBcCdD"
    private val LINK: String = "https://vk.com/im"
    private val LINK_NEW: String = "https://www.baeldung.com/junit-5-runwith"
    private val LINK_A: String = "http://google.com"
    private val LINK_B: String = "http://yahoo.com"


    @Mock
    lateinit var converter: KeyConverterService

    @BeforeEach
    fun init() {
        MockitoAnnotations.openMocks(this)
        Mockito.`when`(converter.keyToId(KEY_A)).thenReturn(ID_A)
        Mockito.`when`(converter.idToKey(ID_A)).thenReturn(KEY_A)
        Mockito.`when`(converter.keyToId(KEY_B)).thenReturn(ID_B)
        Mockito.`when`(converter.idToKey(ID_B)).thenReturn(KEY_B)
    }

    @Test
    fun clientCanAddLinks() {
        val keyA = service.add(LINK_A)
        assertEquals(KeyMapperService.Get.Link(LINK_A), service.getLink(keyA))
        val keyB = service.add(LINK_B)
        assertNotEquals(keyA,keyB)
        assertEquals(KeyMapperService.Get.Link(LINK_B), service.getLink(keyB))
    }

    @Test
    fun clientCanNonTakeLinkIfKeyIsNotFoundInService() {
        assertEquals(KeyMapperService.Get.NotFound(KEY), service.getLink(KEY))
    }
}