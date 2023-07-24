package com.tuple.inventory.repository

import com.tuple.inventory.model.Item
import org.springframework.data.mongodb.repository.MongoRepository

interface ItemRepository : MongoRepository<Item,String> {
}
