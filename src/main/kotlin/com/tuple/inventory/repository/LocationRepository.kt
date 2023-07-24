package com.tuple.inventory.repository

import com.tuple.inventory.model.Location
import org.springframework.data.mongodb.repository.MongoRepository

interface LocationRepository :MongoRepository<Location,String>{
}
