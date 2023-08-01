package com.tuple.inventory.controller

import com.tuple.inventory.model.Supply
import com.tuple.inventory.service.SupplyService
import kotlinx.coroutines.runBlocking
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
@CrossOrigin("*")
@RestController
@RequestMapping("/supply")
class SupplyController(private val supplyService: SupplyService) {
    @GetMapping("/")
    fun getAllSupply(@RequestParam(value = "pageSize",defaultValue = "5", required = false)pageSize:Int,
                     @RequestParam(value = "pageNumber",defaultValue = "0", required = false)pageNumber: Int):ResponseEntity<Page<Supply>>{
        return ResponseEntity(supplyService.getAllSupply(pageNumber,pageSize),HttpStatus.OK)
    }
    @GetMapping("/{supplyId}")
    fun getSupplyBySupplyId(@PathVariable("supplyId")supplyId:String):ResponseEntity<Supply>{
        return ResponseEntity(supplyService.getSupplyBySupplyId(supplyId),HttpStatus.OK)
    }

    @GetMapping("/v1/{itemId}/{locationId}")
    fun getSupplyByItemIdAndLocationId(@PathVariable("itemId")itemId:String,@PathVariable("locationId")locationId:String):ResponseEntity<List<Supply>>{
        return ResponseEntity(supplyService.findSupplyByItemIdAndLocationId(itemId,locationId),HttpStatus.OK)
    }
    @GetMapping("/v2/{supplyType}/{locationId}")
    fun getSupplyBySupplyIdAndLocationId(@PathVariable("supplyType")supplyType:String,@PathVariable("locationId")locationId:String):ResponseEntity<List<Supply>>{
        return ResponseEntity(supplyService.findSupplyBySupplyTypeAndLocationId(supplyType,locationId),HttpStatus.OK)
    }

    @PostMapping("/")
    suspend fun createSupply(@RequestBody supply: Supply):ResponseEntity<Supply> {
        return ResponseEntity(supplyService.createSupply(supply),HttpStatus.CREATED)
    }
    @DeleteMapping("/{supplyId}")
    fun deleteSupply(@PathVariable("supplyId") supplyId: String):ResponseEntity<String>{
        return ResponseEntity(supplyService.deleteSupply(supplyId),HttpStatus.ACCEPTED)
    }

    @PutMapping("/{supplyId}")
    fun updateSupply(@PathVariable("supplyId") supplyId:String, @RequestBody supply: Supply):ResponseEntity<Supply>{
        return ResponseEntity(supplyService.updateSupply(supplyId, supply),HttpStatus.OK)
    }
}