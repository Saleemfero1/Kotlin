package com.tuple.inventory.controller

import com.tuple.inventory.model.Transaction
import com.tuple.inventory.service.TransactionService
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
@CrossOrigin("*")
@RestController
@RequestMapping("/transaction")
class TransactionController(private val transactionService: TransactionService) {
    @GetMapping("/")
    fun getAllTransaction(@RequestParam(value = "pageSize",defaultValue = "5", required = false)pageSize:Int,
                          @RequestParam(value = "pageNumber",defaultValue = "0", required = false)pageNumber: Int):ResponseEntity<Page<Transaction>>{
        return ResponseEntity(transactionService.getAllTransaction(pageNumber,pageSize),HttpStatus.OK)
    }
    @GetMapping("/{transactionId}")
    fun getTransactionById(@PathVariable("transactionId")transactionId:String):ResponseEntity<Transaction>{
        return ResponseEntity(transactionService.getTransactionById(transactionId),HttpStatus.FOUND)
    }
    @GetMapping("v1/{date}")
    fun getTransactionByDate(@PathVariable("date") date:LocalDateTime):ResponseEntity<List<Transaction>>{
        return ResponseEntity(transactionService.getTransactionByDate(date),HttpStatus.OK)
    }
}