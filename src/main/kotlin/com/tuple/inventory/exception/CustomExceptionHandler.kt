package com.tuple.inventory.exception

import com.kotlin.demo.demo.exception.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime

@RestControllerAdvice
class CustomExceptionHandler:ResponseEntityExceptionHandler() {
    @ExceptionHandler(ItemNotFoundException::class)
    fun handleItemNotFoundException(ex:Exception):ResponseEntity<ExceptionDetails>{
        val exceptionDetails = ExceptionDetails(LocalDateTime.now(),ex.message)
        return ResponseEntity(exceptionDetails,HttpStatus.NOT_FOUND)
    }
    @ExceptionHandler(LocationNotFoundException::class)
    fun handleLocationNotFoundException(ex:Exception):ResponseEntity<ExceptionDetails>{
        val exceptionDetails = ExceptionDetails(LocalDateTime.now(),ex.message)
        return ResponseEntity(exceptionDetails,HttpStatus.NOT_FOUND)
    }
    @ExceptionHandler(SupplyNotFoundException::class)
    fun handleSupplyNotFoundException(ex:Exception):ResponseEntity<ExceptionDetails>{
        val exceptionDetails = ExceptionDetails(LocalDateTime.now(),ex.message)
        return ResponseEntity(exceptionDetails,HttpStatus.NOT_FOUND)
    }
    @ExceptionHandler(DemandNotFoundException::class)
    fun handleDemandNotFoundException(ex:Exception):ResponseEntity<ExceptionDetails>{
        val exceptionDetails = ExceptionDetails(LocalDateTime.now(),ex.message)
        return ResponseEntity(exceptionDetails,HttpStatus.NOT_FOUND)
    }
    @ExceptionHandler(ThresholdNotFoundException::class)
    fun handleThresholdNotFoundException(ex:Exception):ResponseEntity<ExceptionDetails>{
        val exceptionDetails = ExceptionDetails(LocalDateTime.now(),ex.message)
        return ResponseEntity(exceptionDetails,HttpStatus.NOT_FOUND)
    }

}