package com.tuple.inventory.service

import com.fasterxml.uuid.Generators
import com.tuple.inventory.exception.ThresholdNotFoundException
import com.tuple.inventory.model.Transaction
import com.tuple.inventory.repository.TransactionRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TransactionService(private val transactionRepository: TransactionRepository) {
    fun getAllTransaction(pageNumber:Int,pageSize:Int): Page<Transaction> {
        val pageable:Pageable = PageRequest.of(pageNumber,pageSize)
        return transactionRepository.findAll(pageable )
    }

    fun getTransactionById(transactionId:String):Transaction{
        val existTransaction = transactionId.let { transactionRepository.existsById(transactionId) }
        if(!existTransaction)
            throw ThresholdNotFoundException("Transaction not found with transactionId:$transactionId")
        return transactionRepository.findById(transactionId).get()
    }

    fun createTransaction(transaction: Transaction):Transaction{
        transaction.transactionId = Generators.timeBasedGenerator().generate().toString()
        return transactionRepository.save(transaction)
    }

    fun getTransactionByDate(date: LocalDateTime): List<Transaction> {
        return transactionRepository.findAllByDate(date)
    }

}