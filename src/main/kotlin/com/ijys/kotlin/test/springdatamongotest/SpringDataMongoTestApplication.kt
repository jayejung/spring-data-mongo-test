package com.ijys.kotlin.test.springdatamongotest

import com.ijys.kotlin.test.springdatamongotest.dto.Ticket
import com.ijys.kotlin.test.springdatamongotest.service.MyMongoTestService
import kotlinx.coroutines.runBlocking
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class SpringDataMongoTestApplication(
) {
    @Bean
    fun init(
        myMongoTestService: MyMongoTestService
    ) = CommandLineRunner {
        println("######## test ######")
        var ticket: Ticket
        runBlocking {
            myMongoTestService.findAndModifyWithSwitch().also { ticket = it }
        }

        println(ticket)
    }
}

fun main(args: Array<String>) {
    runApplication<SpringDataMongoTestApplication>(*args)
}
