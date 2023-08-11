package com.ivand.LinkShortener.services

import org.springframework.stereotype.Service
import java.lang.StringBuilder

@Service
class DefaultKeyConverterService : KeyConverterService {

    val chars = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890-_".toCharArray()
    val charToLong = (0..chars.size-1)
        .map { i-> Pair(chars[i],i.toLong())}
        .toMap()

    override fun keyToId(key: String): Long = key.map { c->charToLong[c]!!}
        .fold(0L,{a,b-> a*chars.size+b})



    override fun idToKey(id: Long): String {
        var n = id
        var builder = StringBuilder()
        while (n != 0L) {
            builder.append(chars[(n % chars.size).toInt()])
            n /= chars.size
        }
        return builder.reverse().toString();
    }
}