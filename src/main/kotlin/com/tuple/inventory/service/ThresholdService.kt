package com.tuple.inventory.service

import com.tuple.inventory.exception.ItemNotFoundException
import com.tuple.inventory.exception.ThresholdNotFoundException
import com.tuple.inventory.model.Threshold
import com.tuple.inventory.repository.ThresholdRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ThresholdService(private val thresholdRepository: ThresholdRepository,
                       private val itemService: ItemService,
                       private val locationService: LocationService
){

    fun getAllThreshold(pageNumber:Int,pageSize:Int): Page<Threshold> {
        val pageable:Pageable = PageRequest.of(pageNumber,pageSize)
       return  thresholdRepository.findAll(pageable)
    }

    fun findThresholdByThresholdId(thresholdId:String): Threshold? {
        val existThreshold = thresholdId.let { thresholdRepository.findById(it) }
        if(existThreshold.isEmpty)
            throw ItemNotFoundException("Threshold Not Found with ThresholdId :$thresholdId")
        return existThreshold.get()
    }

    fun findThresholdByItemIDAndLocationID(itemId: String, locationId: String): Threshold? {
        return thresholdRepository.findByItemIdAndLocationId(itemId, locationId)
    }

    fun createThreshold(threshold: Threshold): Threshold {
        val existThreshold = threshold.let { thresholdRepository.existsByItemIdAndLocationId(it.itemId,it.locationId) }
        if(existThreshold ){
            val existingThreshold = findThresholdByItemIDAndLocationID(threshold.itemId,threshold.locationId)
            throw ItemNotFoundException("Threshold exist with thresholdId:${existingThreshold?.thresholdId}")
        }
        if(!(itemService.isItemExist(threshold.itemId) && locationService.isLocationExist(threshold.locationId)&& checkValidQuantity(threshold.minQuantity,threshold.maxQuantity)))
            throw ThresholdNotFoundException("Provide correct values!")
        return thresholdRepository.save(threshold)
    }

    fun deleteThreshold(thresholdId:String):String{
        val existThreshold = thresholdId.let { thresholdRepository.existsById(it)}
        if (!existThreshold)
            throw ItemNotFoundException("Threshold Not Found with thresholdId:${thresholdId}")
        thresholdRepository.deleteById(thresholdId)
        return "Threshold with threshold: $thresholdId is deleted!"
    }

    fun updateThreshold(thresholdId: String, threshold: Threshold): Threshold? {
        val existThreshold = thresholdId.let { thresholdRepository.findById(it)}
        if (existThreshold .isEmpty) {
            throw ItemNotFoundException("Threshold Not Found with thresholdId:${thresholdId}")
        };
        val getExistThreshold = existThreshold.get()
        if(checkValidQuantity(threshold.minQuantity,threshold.maxQuantity)){
            getExistThreshold.minQuantity= threshold.minQuantity
            getExistThreshold.maxQuantity= threshold.maxQuantity
            thresholdRepository.save(getExistThreshold)
        }
        return getExistThreshold
    }

    fun isThresholdExist(thresholdId: String): Boolean {
        return  thresholdRepository.existsById(thresholdId)
    }
    fun isThresholdExistByItemAndLocation(itemId: String,locationId:String): Boolean {
        return  thresholdRepository.existsByItemIdAndLocationId(itemId,locationId)
    }
    fun checkValidQuantity(minQuantity:Int,maxQuantity:Int):Boolean{
        return minQuantity<maxQuantity
    }
}