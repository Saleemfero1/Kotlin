package com.tuple.inventory.dto
class Availability(
    val itemId:String,
    val locationId:String,
    val totalSupply:Int,
    val totalDemand:Int,
    val quantity:Int)