package com.tuple.inventory.service


import com.tuple.inventory.dto.Availability
import com.tuple.inventory.repository.ItemRepository
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service

@Service
class AvailabilityService(
    private val itemRepository: ItemRepository,
    private val supplyService: SupplyService,
    private val demandService: DemandService
) {

    suspend  fun getAvailabilityOfItemAtSpecificLocation(itemId: String, locationId: String): Availability {
        val totalSupply:Int =supplyService.getTotalSupplyByItemIdAndLocationId(itemId,locationId)
        val totalDemand:Int= demandService.getTotalDemandByItemIdAndLocationId(itemId,locationId)
        return Availability(itemId,locationId,totalSupply,totalDemand,totalSupply-totalDemand)
    }

    suspend fun getAvailabilityOfItemAtAllLocation(itemId: String): Availability {
        val totalSupply:Int =supplyService.getTotalSupplyOfItemAtAllLocation(itemId)
        val totalDemand:Int= demandService.getTotalDemandOfItemAtAllLocation(itemId)
        return Availability(itemId,"NETWORK",totalSupply,totalDemand,totalSupply-totalDemand)
    }

}