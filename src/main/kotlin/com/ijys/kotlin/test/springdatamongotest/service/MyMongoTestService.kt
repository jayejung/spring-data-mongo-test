package com.ijys.kotlin.test.springdatamongotest.service

import com.ijys.kotlin.test.springdatamongotest.dao.MyMongoTestDAO
import com.ijys.kotlin.test.springdatamongotest.dto.Ticket
import org.springframework.stereotype.Service

@Service
class MyMongoTestService(
    private val myMongoTestDAO: MyMongoTestDAO
) {
    suspend fun findAndModifyWithSwitch(): Ticket {
        return myMongoTestDAO.findAndModifyWithSwitch()
    }
}