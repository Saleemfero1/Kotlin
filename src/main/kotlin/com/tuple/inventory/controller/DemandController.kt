package com.tuple.inventory.controller

import com.tuple.inventory.model.Demand
import com.tuple.inventory.service.DemandService
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
@CrossOrigin("*")
@RestController
@RequestMapping("/demand")
class DemandController(private val demandService: DemandService) {
    @GetMapping("/")
    fun getAllDemand(@RequestParam(value = "pageSize",defaultValue = "5", required = false)pageSize:Int,
                     @RequestParam(value = "pageNumber",defaultValue = "0", required = false)pageNumber: Int): ResponseEntity<Page<Demand>> {
        return ResponseEntity(demandService.getAllDemand(pageNumber,pageSize), HttpStatus.OK);
    }
    @GetMapping("/{demandId}")
    fun getDemandById(@PathVariable("demandId") demandId:String): ResponseEntity<Demand> {
        return ResponseEntity(demandService.getDemandByDemandId(demandId), HttpStatus.OK);
    }

    @GetMapping("/v1/{itemId}/{locationId}")
    fun getDemandByItemIdAndLocationId(@PathVariable("itemId")itemId:String,@PathVariable("locationId")locationId:String):ResponseEntity<List<Demand>>{
        return ResponseEntity(demandService.findDemandByItemIdAndLocationId(itemId,locationId),HttpStatus.OK)
    }
    @GetMapping("/v2/{demandType}/{locationId}")
    fun getDemandByDemandIdAndLocationId(@PathVariable("demandType")demandType:String,@PathVariable("locationId")locationId:String):ResponseEntity<List<Demand>>{
        return ResponseEntity(demandService.findDemandByDemandTypeAndLocationId(demandType,locationId),HttpStatus.OK)
    }
    @PostMapping("/")
    fun createDemand(@RequestBody demand: Demand): ResponseEntity<Demand> {
        return ResponseEntity(demandService.createDemand(demand), HttpStatus.CREATED)
    }
    @DeleteMapping("/{demandId}")
    fun deleteDemand(@PathVariable("demandId") demandId:String): ResponseEntity<String> {
        return ResponseEntity(demandService.deleteDemand(demandId), HttpStatus.ACCEPTED)
    }

    @PutMapping("/{demandId}")
    fun updateItem(@PathVariable("demandId") demandId:String, @RequestBody demand: Demand): ResponseEntity<Demand> {
        return ResponseEntity(demandService.updateDemand(demandId,demand), HttpStatus.OK)
    }

}