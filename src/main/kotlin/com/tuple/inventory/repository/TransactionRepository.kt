package com.tuple.inventory.repository


import com.tuple.inventory.model.Transaction
import org.springframework.data.mongodb.repository.MongoRepository
import java.time.LocalDateTime

interface TransactionRepository:MongoRepository<Transaction,String> {
    abstract fun findAllByDate(date: LocalDateTime): List<Transaction>

}