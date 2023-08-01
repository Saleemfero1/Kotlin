package com.tuple.inventory.controller


import com.tuple.inventory.dto.Availability
import com.tuple.inventory.service.AvailabilityService
import com.tuple.inventory.service.DemandService
import com.tuple.inventory.service.SupplyService
import kotlinx.coroutines.runBlocking
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
@CrossOrigin("*")
@RestController
@RequestMapping("/api/availability")
class AvailabilityController(
    private val supplyService: SupplyService,
    private val demandService: DemandService,
    private val availabilityService: AvailabilityService
    ) {

    @GetMapping("/v1/{itemId}/{locationId}")
    suspend fun getAvailabilityOfItemAtSpecificLocation(@PathVariable("itemId")itemId:String,@PathVariable("locationId")locationId:String):ResponseEntity<Availability>{
        return ResponseEntity(availabilityService.getAvailabilityOfItemAtSpecificLocation(itemId,locationId),HttpStatus.OK)
    }

    @GetMapping("/v2/{itemId}")
    suspend fun getAvailabilityOfItemAtAllLocation(@PathVariable("itemId")itemId:String):ResponseEntity<Availability>{
        return ResponseEntity(availabilityService.getAvailabilityOfItemAtAllLocation(itemId),HttpStatus.OK)
    }
    @GetMapping("/v3")
    suspend fun dummyControl():ResponseEntity<Int> {
        return ResponseEntity(availabilityService.dummy(),HttpStatus.OK)
    }

}