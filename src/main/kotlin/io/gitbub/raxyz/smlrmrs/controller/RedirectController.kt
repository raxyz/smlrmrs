package io.gitbub.raxyz.smlrmrs.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletResponse

@Controller
@RequestMapping("/{key}")
class RedirectController {

    @RequestMapping()
    fun redirect(@PathVariable("key") key: String, response: HttpServletResponse) {
        if (key.equals("aAbBcCdD")) {
            response.setHeader(HEADER_NAME_LOCATION, "https://www.google.com")
            response.status = 302
        } else {
            response.status = 404
        }
    }

    companion object {
        val HEADER_NAME_LOCATION = "Location"
    }
}