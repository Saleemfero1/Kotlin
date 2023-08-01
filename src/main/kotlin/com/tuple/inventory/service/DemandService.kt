package com.tuple.inventory.service

import com.fasterxml.uuid.Generators
import com.tuple.inventory.enum.DemandType
import com.tuple.inventory.exception.DemandNotFoundException
import com.tuple.inventory.exception.ItemNotFoundException
import com.tuple.inventory.model.Demand
import com.tuple.inventory.repository.DemandRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

import org.springframework.stereotype.Service

@Service
class DemandService(private val demandRepository: DemandRepository) {

    fun getAllDemand(pageNumber:Int,pageSize:Int):Page<Demand>{
        val pageable:Pageable = PageRequest.of(pageNumber,pageSize)
        return demandRepository.findAll(pageable)
    }

    fun getDemandByDemandId(demandId: String): Demand {
        val existDemand = demandId.let { demandRepository.findById(it) }
        if(existDemand.isEmpty)
            throw DemandNotFoundException("Demand Not Found with demandId :$demandId")
        return existDemand.get()
    }
    fun  createDemand (demand: Demand): Demand {
        demand.demandId = Generators.timeBasedGenerator().generate().toString()
        return demandRepository.save(demand)
    }

    fun deleteDemand(demandId: String):String{
        val existDemand = demandId.let { demandRepository.existsById(demandId)}
        if(!existDemand){
            throw DemandNotFoundException("Demand not found with demand:$demandId")
        }
        demandRepository.deleteById(demandId)
        return "Demand delete with demandId:$demandId"
    }

    fun updateDemand(demandId: String, demand: Demand): Demand? {
        val existDemand = demandId.let { demandRepository.findById(it)}
        if (existDemand .isEmpty) {
            throw ItemNotFoundException("Demand not found with demandId:${demandId}")
        };
        val getExistDemand = existDemand.get()
        getExistDemand.demandType= demand.demandType
        getExistDemand.quantity= demand.quantity
        getExistDemand.itemId= demand.itemId
        getExistDemand.locationId = demand.locationId
        return demandRepository.save(getExistDemand)
    }


    fun findDemandByItemIdAndLocationId(itemId: String, locationId: String):List<Demand>{
        return demandRepository.findAllByItemIdAndLocationId(itemId,locationId)
    }

    fun findDemandAtAllLocation (itemId: String):List<Demand>{
        return demandRepository.findAllByItemId(itemId)
    }
    fun findDemandByDemandTypeAndLocationId(demandType:String,locationId: String):List<Demand>{
        return demandRepository.findAllByDemandTypeAndLocationId(demandType,locationId)
    }

    suspend fun getTotalDemandByItemIdAndLocationId(itemId:String,locationId: String):Int{
        val demandList:List<Demand> = findDemandByItemIdAndLocationId(itemId,locationId)
        return demandList.filter { it.demandType == DemandType.HARDPROMISED }.sumOf { it.quantity }

    }


    suspend fun getTotalDemandOfItemAtAllLocation(itemId:String):Int{
        val demandList:List<Demand> = findDemandAtAllLocation(itemId)
        return demandList.filter { it.demandType == DemandType.HARDPROMISED }.sumOf { it.quantity }
    }

}