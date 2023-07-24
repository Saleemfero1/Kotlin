package com.tuple.inventory.repository

import com.tuple.inventory.model.Supply
import org.springframework.data.mongodb.repository.MongoRepository

interface SupplyRepository : MongoRepository<Supply,String> {
    abstract fun findAllByItemIdAndLocationId(itemId:String,locationId:String):List<Supply>
    abstract fun findAllByItemId(itemId: String):List<Supply>
    abstract fun findAllBySupplyTypeAndLocationId(supplyType: String, locationId: String): List<Supply>
}