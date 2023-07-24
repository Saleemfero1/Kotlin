package com.tuple.inventory.service
import com.fasterxml.uuid.Generators
import com.tuple.inventory.enum.SupplyType
import com.tuple.inventory.exception.ItemNotFoundException
import com.tuple.inventory.exception.SupplyNotFoundException
import com.tuple.inventory.exception.ThresholdNotFoundException
import com.tuple.inventory.model.Supply
import com.tuple.inventory.model.Transaction
import com.tuple.inventory.repository.SupplyRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class SupplyService(private val supplyRepository: SupplyRepository,
                    private val thresholdService: ThresholdService,
                    private val transactionService: TransactionService
) {

    fun getAllSupply(pageNumber:Int,pageSize:Int):Page<Supply>{
        val pageable:Pageable=PageRequest.of(pageNumber,pageSize)
        return supplyRepository.findAll(pageable)
    }

    fun getSupplyBySupplyId(supplyId: String):Supply{
        val existSupply = supplyId.let { supplyRepository.findById(it) }
        if(existSupply.isEmpty)
            throw SupplyNotFoundException("Supply Not Found with supplyId :$supplyId")
        return existSupply.get()
    }
    fun  createSupply(supply: Supply):Supply{
        supply.supplyId =Generators.timeBasedGenerator().generate().toString()
        if(!(thresholdService.isThresholdExistByItemAndLocation(supply.itemId,supply.locationId)))
            throw ThresholdNotFoundException("Threshold not found for item and location")
        val transaction = Transaction(null,supply.itemId,supply.locationId,"Supply + ${supply.supplyType}",supply.quantity,
            LocalDateTime.now())
        transactionService.createTransaction(transaction)
        return supplyRepository.save(supply)

    }

    fun deleteSupply(supplyId: String):String{
        val existSupply = supplyId.let { supplyRepository.existsById(supplyId)}
        if(!existSupply){
            throw SupplyNotFoundException("Supply not found with supplyId:$supplyId")
        }
        supplyRepository.deleteById(supplyId)
        return "Supply delete with supplyId:$supplyId"
    }

    fun updateSupply(supplyId: String, supply: Supply): Supply? {
        val existSupply = supplyId.let { supplyRepository.findById(it)}
        if (existSupply .isEmpty) {
            throw ItemNotFoundException("Supply not found with supplyId:${supplyId}")
        };
        val getExistSupply = existSupply.get()
        getExistSupply.supplyType= supply.supplyType
        getExistSupply.quantity= supply.quantity
        getExistSupply.itemId= supply.itemId
        getExistSupply.locationId = supply.locationId
        return supplyRepository.save(getExistSupply)
    }

    fun findSupplyByItemIdAndLocationId(itemId: String, locationId: String):List<Supply>{
        return supplyRepository.findAllByItemIdAndLocationId(itemId,locationId)
    }

    fun findSupplyAtAllLocation (itemId: String):List<Supply>{
        return supplyRepository.findAllByItemId(itemId)
    }
    fun findSupplyBySupplyTypeAndLocationId(supplyType:String,locationId: String):List<Supply>{
        return supplyRepository.findAllBySupplyTypeAndLocationId(supplyType,locationId)
    }

    suspend  fun getTotalSupplyByItemIdAndLocationId(itemId:String,locationId: String):Int{
        val supplyList:List<Supply> = findSupplyByItemIdAndLocationId(itemId,locationId)
        var totalSupply:Int = 0
        if (supplyList.isEmpty())
             return totalSupply
        else{
            for (supply in supplyList){
                if(supply.supplyType== SupplyType.ONHAND)
                    totalSupply+=supply.quantity
            }
        }
        return totalSupply
    }
    suspend fun getTotalSupplyOfItemAtAllLocation(itemId:String):Int{
        val supplyList:List<Supply> = findSupplyAtAllLocation(itemId)
        var totalSupply:Int = 0
        if (supplyList.isEmpty())
            return 0
        else{
            for (supply in supplyList){
                if(supply.supplyType==SupplyType.ONHAND)
                    totalSupply+=supply.quantity
            }
        }
        return totalSupply
    }
}