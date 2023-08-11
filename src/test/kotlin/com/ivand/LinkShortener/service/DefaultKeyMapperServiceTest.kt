package com.ivand.LinkShortener.service

import com.ivand.LinkShortener.services.DefaultKeyMapperService
import com.ivand.LinkShortener.services.KeyMapperService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DefaultKeyMapperServiceTest {

    val service: KeyMapperService = DefaultKeyMapperService()
    private val KEY: String = "aAbBcCdD"
    private val LINK: String = "https://vk.com/im"
    private val LINK_NEW: String = "https://www.baeldung.com/junit-5-runwith"


    @Test
    fun clientCanAddNewKeyWithLink(){
        assertEquals(KeyMapperService.Add.Success(KEY,LINK), service.add(KEY,LINK))
        assertEquals(KeyMapperService.Get.Link(LINK),service.getLink(KEY))
    }


    @Test
    fun clientCannotAddExistingKey(){
        service.add(KEY,LINK)
        assertEquals(KeyMapperService.Add.AlreadyExists(KEY),service.add(KEY,LINK_NEW))
        assertEquals(KeyMapperService.Get.Link(LINK),service.getLink(KEY))
    }

    @Test
    fun clientCanNonTakeLinkIfKeyIsNotFoundInService(){
        assertEquals(KeyMapperService.Get.NotFound(KEY),service.getLink(KEY))
    }
}