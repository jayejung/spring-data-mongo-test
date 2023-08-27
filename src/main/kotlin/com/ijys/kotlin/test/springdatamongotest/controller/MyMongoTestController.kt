package com.ijys.kotlin.test.springdatamongotest.controller

import com.ijys.kotlin.test.springdatamongotest.dto.Ticket
import com.ijys.kotlin.test.springdatamongotest.service.MyMongoTestService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("mymongo")
class MyMongoTestController(
    private val myMongoTestService: MyMongoTestService
) {

    @GetMapping("/test")
    suspend fun getFindAndModifySwitch(): Ticket {
        val ticket = myMongoTestService.findAndModifyWithSwitch()
        return ticket
    }
}