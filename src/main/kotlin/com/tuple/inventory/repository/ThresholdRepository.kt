package com.tuple.inventory.repository

import com.tuple.inventory.model.Threshold
import org.springframework.data.mongodb.repository.MongoRepository

interface ThresholdRepository:MongoRepository<Threshold,String> {
    abstract fun existsByItemIdAndLocationId(itemId: String, locationId: String): Boolean
    abstract fun findByItemIdAndLocationId(itemId: String, locationId: String): Threshold
}