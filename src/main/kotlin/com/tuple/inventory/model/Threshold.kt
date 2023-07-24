package com.tuple.inventory.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "threshold")

class Threshold(
    @field:Id
    var thresholdId:String?=null,
    var itemId:String,
    var locationId:String,
    var minQuantity:Int,
    var maxQuantity:Int,
)