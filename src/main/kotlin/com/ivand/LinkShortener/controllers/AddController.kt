package com.ivand.LinkShortener.controllers

import com.ivand.LinkShortener.services.KeyMapperService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping()
class AddController {

    @Value("\${link.prefix}")
    lateinit var prefix:String

    @Autowired
    lateinit var service: KeyMapperService

    @PostMapping("/add")
    fun addRest(@RequestBody request: AddRequest) =
        ResponseEntity.ok(AddResponse(request.link, service.add(request.link)))

    @PostMapping("/addhtml")
    fun addhtml(model: Model, @RequestParam("link") link: String): String {
        var result = add(link)
        model.addAttribute("link",result.link)
        model.addAttribute("keyed",prefix+result.key)
        return "result"
    }

    data class AddRequest(val link: String)

    data class AddResponse(val link: String, val key: String)

    private fun add(link:String) = AddResponse(link,service.add(link))
}