package com.ivand.LinkShortener.services

import com.ivand.LinkShortener.model.Link
import com.ivand.LinkShortener.repository.LinkRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class DefaultKeyMapperService : KeyMapperService {

    @Autowired
    lateinit var converter:KeyConverterService

    @Autowired
    lateinit var repository: LinkRepository

    override fun getLink(key: String): KeyMapperService.Get {
        val result = repository.findById(converter.keyToId(key))
        return if(result.isPresent){
            KeyMapperService.Get.Link(result.get().text)
        }else{
            KeyMapperService.Get.NotFound(key)
        }
    }


    @Transactional
    override fun add(link: String): String {
       return converter.idToKey(repository.save(Link(link)).id)
    }


}


