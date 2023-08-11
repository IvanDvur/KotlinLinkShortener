package com.ivand.LinkShortener.controllers

import com.ivand.LinkShortener.services.KeyMapperService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping
class RedirectController{

    @Autowired
    private lateinit var service: KeyMapperService

    @RequestMapping("/{key}")
    fun redirect(@PathVariable(name = "key") key: String, response: HttpServletResponse) {
        val result = service.getLink(key)
        when(result){
            is KeyMapperService.Get.Link->{
                response.setHeader(HEADER_NAME,result.link)
                response.status = 302
            }
            is KeyMapperService.Get.NotFound->{
                response.status = 404
            }
        }
    }

    companion object {
        private val HEADER_NAME = "Location"
    }
}