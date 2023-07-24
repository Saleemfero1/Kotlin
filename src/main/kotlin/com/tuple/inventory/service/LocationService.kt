package com.tuple.inventory.service

import com.tuple.inventory.exception.LocationNotFoundException
import com.tuple.inventory.model.Location
import com.tuple.inventory.repository.LocationRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class LocationService(private val locationRepository : LocationRepository) {

    fun getAllLocations(pageNumber: Int,pageSize:Int):Page<Location>{
        val pageable:Pageable = PageRequest.of(pageNumber,pageSize)
        return locationRepository.findAll(pageable)
    }

    fun findLocationByLocationId(locationId:String): Location? {
        val existLocation = locationId.let { locationRepository.findById(it) }
        if(existLocation.isEmpty)
            throw LocationNotFoundException("Location Not Found with locationId :$locationId")
        return existLocation.get()
    }

    fun createLocation(location: Location): Location {
        val existLocation = location.locationId.let { locationRepository.existsById(it) }
        if(existLocation){
            throw LocationNotFoundException("Location exist with locationId:${location.locationId}")
        }
        locationRepository.save(location)
        return location
    }

    fun deleteLocation(locationId:String):String{
        val existLocation = locationId.let { locationRepository.existsById(it) }
        if (!existLocation)
            throw LocationNotFoundException("Location Not Found with locationId:${locationId}")
        locationRepository.deleteById(locationId)
        return "Location with locationId: $locationId is deleted!"
    }

    fun updateLocation(locationId: String, location: Location): Location? {
        val existLocation = locationId.let { locationRepository.findById(it) }
        if(existLocation.isEmpty){
            throw LocationNotFoundException("Location not exit with locationId:$locationId")
        }
        val getExistLocation = existLocation.get()
        getExistLocation.locationDesc = location.locationDesc
        getExistLocation.locationType = location.locationType
        getExistLocation.pickupAllowed = location.pickupAllowed
        getExistLocation.shippingAllowed = location.shippingAllowed
        getExistLocation.deliveryAllowed = location.deliveryAllowed
        getExistLocation.state = location.state
        getExistLocation.city = location.city
        getExistLocation.country = location.country
        getExistLocation.pinCode = location.pinCode
        return locationRepository.save(getExistLocation)
    }

   fun isLocationExist(locationId: String):Boolean{
       return locationRepository.existsById(locationId)
   }
}