package com.ijys.kotlin.test.springdatamongotest.dto

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "testCol")
data class Ticket(
    @Id
    val _id: Int,
    val rM: Int,
    val oM: Int,
    val status: String
)