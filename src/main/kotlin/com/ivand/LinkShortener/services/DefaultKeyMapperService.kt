package com.ivand.LinkShortener.services

import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap


@Service
class DefaultKeyMapperService : KeyMapperService {

    private val map: MutableMap<String, String> = ConcurrentHashMap()

    override fun add(key: String, link: String): KeyMapperService.Add {
        return if (map.containsKey(key)) {
            KeyMapperService.Add.AlreadyExists(key)
        } else {
            map.put(key, link)
            KeyMapperService.Add.Success(key, link)
        }
    }

    override fun getLink(key: String): KeyMapperService.Get = if (map.containsKey(key)) {
        KeyMapperService.Get.Link(map.get(key)!!)
    } else {
        KeyMapperService.Get.NotFound(key)
    }


}