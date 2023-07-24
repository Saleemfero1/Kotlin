package com.tuple.inventory.model

import com.tuple.inventory.enum.DemandType
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "demand")
class Demand (
 @field:Id
     var demandId:String?=null,
 var itemId:String,
 var locationId:String,
 var demandType: DemandType,
 var quantity:Int,
)
