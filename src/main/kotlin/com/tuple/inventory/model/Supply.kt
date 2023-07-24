package com.tuple.inventory.model

import com.tuple.inventory.enum.SupplyType
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "supply")
class Supply(
    @field:Id
    var supplyId:String?=null,
    var itemId:String,
    var locationId:String,
    var supplyType: SupplyType,
    var quantity:Int
)
