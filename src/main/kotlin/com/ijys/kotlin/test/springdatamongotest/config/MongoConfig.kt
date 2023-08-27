package com.ijys.kotlin.test.springdatamongotest.config

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper
import org.springframework.data.mongodb.core.convert.MappingMongoConverter

@Configuration
@EnableMongoAuditing
class MongoConfig : AbstractReactiveMongoConfiguration() {
    override fun getDatabaseName(): String {
        return "test"
    }

    override fun reactiveMongoClient(): MongoClient {
        val connectionString = ConnectionString("mongodb://127.0.0.1:27017/test")
        val mongoClientSettings = MongoClientSettings
            .builder()
            .applyConnectionString(connectionString)
            .build()

        return MongoClients.create(mongoClientSettings)
    }

    @Bean
    override fun reactiveMongoTemplate(
        databaseFactory: ReactiveMongoDatabaseFactory,
        mongoConverter: MappingMongoConverter
    ): ReactiveMongoTemplate =
        mongoConverter.let {
            it.setTypeMapper(DefaultMongoTypeMapper(null))
            ReactiveMongoTemplate(databaseFactory, it)
        }
}