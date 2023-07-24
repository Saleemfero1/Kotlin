package com.tuple.inventory.repository

import com.tuple.inventory.model.Demand
import org.springframework.data.mongodb.repository.MongoRepository

interface DemandRepository : MongoRepository<Demand,String> {
    abstract fun findAllByItemIdAndLocationId(itemId: String, locationId: String): List<Demand>
    abstract fun findAllByItemId(itemId: String): List<Demand>
    abstract fun findAllByDemandTypeAndLocationId(demandType: String, locationId: String): List<Demand>
}