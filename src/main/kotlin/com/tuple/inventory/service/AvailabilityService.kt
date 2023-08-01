package com.tuple.inventory.service


import com.tuple.inventory.dto.Availability
import com.tuple.inventory.repository.ItemRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service

@Service
class AvailabilityService(
    private val supplyService: SupplyService,
    private val demandService: DemandService
) {

    suspend  fun getAvailabilityOfItemAtSpecificLocation(itemId: String, locationId: String): Availability = coroutineScope {
        val getTotalSupply = async { supplyService.getTotalSupplyByItemIdAndLocationId(itemId,locationId)}
        val getTotalDemand = async {  demandService.getTotalDemandByItemIdAndLocationId(itemId,locationId)}
        val totalSupply = getTotalSupply.await()
        val totalDemand = getTotalDemand.await()
        return@coroutineScope Availability(itemId,locationId,totalSupply,totalDemand,totalSupply-totalDemand)
    }

    suspend fun getAvailabilityOfItemAtAllLocation(itemId: String): Availability = coroutineScope{
        val getTotalSupply = async { supplyService.getTotalSupplyOfItemAtAllLocation(itemId)}
        val getTotalDemand = async { demandService.getTotalDemandOfItemAtAllLocation(itemId)}
        val  totalSupply = getTotalSupply.await()
        val totalDemand = getTotalDemand.await()
        return@coroutineScope Availability(itemId,"NETWORK",totalSupply,totalDemand,totalSupply-totalDemand)
    }


    //example
    suspend fun dummy() :Int = coroutineScope{
        val getTaskOne =async {supplyService.TaskOne()}
        val getTaskTwo =async {supplyService.TaskTwo()}
        val taskOne = getTaskOne.await()
        val taskTwo = getTaskTwo.await()
      return@coroutineScope taskOne+taskTwo
    }

}