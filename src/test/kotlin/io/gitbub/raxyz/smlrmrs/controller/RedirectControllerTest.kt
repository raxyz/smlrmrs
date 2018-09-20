package io.gitbub.raxyz.smlrmrs.controller

import io.gitbub.raxyz.smlrmrs.SmlrmrsApplication
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [SmlrmrsApplication::class])
@WebAppConfiguration
class RedirectControllerTest {
    @Autowired
    lateinit var webApplicationContext: WebApplicationContext

    lateinit var mockMvc: MockMvc

    @Before
    fun init() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build()
    }

    private val PATH = "/aAbBcCdD"
    private val REDIRECT_STATUS: Int = 302
    private val HEADER_NAME = "Location"
    private val HEADER_VALUE = "https://www.google.com"


    @Test
    fun controllerMustRedirectUsWhenRequestIsSuccessful() {
        mockMvc.perform(get(PATH))
                .andExpect(status().`is`(REDIRECT_STATUS))
                .andExpect(header().string(HEADER_NAME, HEADER_VALUE))
    }

    private val BAD_PATH = "/ololo"
    private val NOT_FOUND: Int = 404

    @Test
    fun controllerMustReturn404IfBadKey() {
        mockMvc.perform(get(BAD_PATH))
                .andExpect(status().`is`(NOT_FOUND))
    }
}