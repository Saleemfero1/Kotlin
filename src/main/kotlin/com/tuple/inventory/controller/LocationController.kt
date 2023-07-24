package com.tuple.inventory.controller

import com.tuple.inventory.model.Location
import com.tuple.inventory.service.LocationService
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/location")
class LocationController(private val locationService : LocationService?=null){
    @GetMapping("/")
    fun getAllLocation(@RequestParam(value = "pageSize",defaultValue = "5", required = false)pageSize:Int,
                       @RequestParam(value = "pageNumber",defaultValue = "0", required = false)pageNumber: Int):ResponseEntity<Page<Location>>{
        return ResponseEntity(locationService?.getAllLocations(pageNumber,pageSize),HttpStatus.OK)
    }

    @PostMapping("/")
    fun createLocation(@RequestBody location: Location):ResponseEntity<Location>{
        return ResponseEntity(locationService?.createLocation(location),HttpStatus.CREATED)
    }
    @GetMapping("/{locationId}")
    fun getLocationByLocationId(@PathVariable("locationId")locationId:String):ResponseEntity<Location>{
        return ResponseEntity(locationService?.findLocationByLocationId(locationId),HttpStatus.FOUND)
    }

    @DeleteMapping("/{locationId}")
    fun deleteLocation(@PathVariable("locationId") locationId: String):ResponseEntity<String>{
        return ResponseEntity(locationService?.deleteLocation(locationId),HttpStatus.ACCEPTED)
    }

    @PutMapping("/{locationId}")
    fun updateLocation(@PathVariable("locationId")locationId:String, @RequestBody location: Location):ResponseEntity<Location>{
    return ResponseEntity(locationService?.updateLocation(locationId,location),HttpStatus.OK)
    }
}