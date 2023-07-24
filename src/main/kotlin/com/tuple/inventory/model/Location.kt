package com.tuple.inventory.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "locations")
class Location (
    @field:Id
    var locationId: String,
    var locationDesc:String,
    var locationType:String,
    var addressLineOne:String,
    var pickupAllowed:Boolean,
    var shippingAllowed:Boolean,
    var deliveryAllowed:Boolean,
    var addressLineTwo:String,
    var city :String,
    var state:String,
    var country:String,
    var pinCode:String,
)