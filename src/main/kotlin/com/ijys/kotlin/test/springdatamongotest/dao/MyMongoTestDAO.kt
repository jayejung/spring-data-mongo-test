package com.ijys.kotlin.test.springdatamongotest.dao

import com.ijys.kotlin.test.springdatamongotest.dto.Ticket
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.data.mongodb.core.FindAndModifyOptions
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.aggregation.AggregationUpdate
import org.springframework.data.mongodb.core.aggregation.ComparisonOperators
import org.springframework.data.mongodb.core.aggregation.ConditionalOperators
import org.springframework.data.mongodb.core.aggregation.ConditionalOperators.Switch.CaseOperator
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Repository

@Repository
class MyMongoTestDAO(
    private val reactiveMongoTemplate: ReactiveMongoTemplate
) {

    /**
    db.testCol.findAndModify({
    query: {"_id": 2},
    update: [
    {
    $set: {"status": {
    $switch: {
    branches: [
    { case: { $eq: ["$rM", "$oM"]}, then: "reissued"},
    { case: { $ne: ["$rM", "$oM"]}, then: "transreissued"}
    ]
    }
    }
    }
    }],
    new: true
    }
    );

     */
    suspend fun findAndModifyWithSwitch(): Ticket {
        val aggregationUpdate: AggregationUpdate = AggregationUpdate.update()
            .set("status")
            .toValue(
                ConditionalOperators
                    .switchCases(
                        CaseOperator.`when`(ComparisonOperators.Eq.valueOf("rM").equalTo("oM")).then("reissued"),
                        CaseOperator.`when`(ComparisonOperators.Ne.valueOf("rM").notEqualTo("oM")).then("transissued")
                    )
            )
        return reactiveMongoTemplate.findAndModify(
            Query.query(
                Criteria.where("_id").isEqualTo(2)
            ),
            aggregationUpdate,
            FindAndModifyOptions.options().returnNew(true),
            Ticket::class.java
        ).awaitSingle()
    }
}