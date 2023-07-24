package com.tuple.inventory.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
@Document(collection = "transaction")
class Transaction(
    @field:Id
    var transactionId :String?=null,
    var itemId:String,
    var locationId:String,
    var transactionType:String,
    var quantity:Int,
    var date :LocalDateTime
)
