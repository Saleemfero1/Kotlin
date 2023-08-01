package com.tuple.inventory.controller

import com.tuple.inventory.model.Threshold
import com.tuple.inventory.service.ThresholdService
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
@CrossOrigin("*")
@RestController
@RequestMapping("/threshold")
class ThresholdController(private val thresholdService: ThresholdService?=null) {
    @GetMapping("/")
    fun getAllThreshold(@RequestParam(value = "pageSize",defaultValue = "5", required = false)pageSize:Int,
                        @RequestParam(value = "pageNumber",defaultValue = "0", required = false)pageNumber: Int):ResponseEntity<Page<Threshold>>{
     return ResponseEntity(thresholdService?.getAllThreshold(pageNumber,pageSize),HttpStatus.OK)
    }
    @PostMapping("/")
    fun createThreshold(@RequestBody threshold: Threshold):ResponseEntity<Threshold>{
        return ResponseEntity(thresholdService?.createThreshold(threshold),HttpStatus.OK)
    }
    @GetMapping("/{thresholdId}")
    fun getThresholdById(@PathVariable("thresholdId")thresholdId: String):ResponseEntity<Threshold>{
        return ResponseEntity(thresholdService?.findThresholdByThresholdId(thresholdId),HttpStatus.OK)
    }
    @DeleteMapping("/{thresholdId}")
    fun deleteThresholdById(@PathVariable("thresholdId")thresholdId:String):ResponseEntity<String>{
        return ResponseEntity(thresholdService?.deleteThreshold(thresholdId),HttpStatus.ACCEPTED)
    }
    @PutMapping("/{thresholdId}")
    fun updateThreshold(@PathVariable("thresholdId")thresholdId:String,@RequestBody threshold: Threshold):ResponseEntity<Threshold>{
        return ResponseEntity(thresholdService?.updateThreshold(thresholdId,threshold),HttpStatus.OK)
    }
    @GetMapping("/{itemId}/{locationId}")
    fun getThresholdByItemIdAndLocationId(@PathVariable("itemId")itemId:String,@PathVariable("locationId")locationId:String):ResponseEntity<Threshold>{
    return ResponseEntity(thresholdService?.findThresholdByItemIDAndLocationID(itemId,locationId),HttpStatus.OK)
    }

}