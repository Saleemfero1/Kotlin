package com.tuple.inventory.model
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "items")
class Item(
    @field:Id
     var itemId: String,
     var itemName: String,
     var itemDescription: String,
     var itemCategory: String,
     var itemType : String,
     var price :Double,
     var status :Boolean,
     var pickUpAllowed:Boolean,
     var shippingAllowed:Boolean,
     var deliveryAllowed:Boolean
)