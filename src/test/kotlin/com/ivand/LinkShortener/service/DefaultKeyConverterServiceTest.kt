package com.ivand.LinkShortener.service

import com.ivand.LinkShortener.services.DefaultKeyConverterService
import com.ivand.LinkShortener.services.KeyConverterService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.Random

class DefaultKeyConverterServiceTest {


    val service: KeyConverterService = DefaultKeyConverterService()

    @Test
    fun givenIdMustBeConvertible(){
        val rand = Random()
        for (i in 0..1000){
            val initialId = Math.abs(rand.nextLong())
            val key = service.idToKey(initialId)
            val id = service.keyToId(key)
            assertEquals(initialId,id)
        }
    }
}