package com.tuple.inventory.service

import com.tuple.inventory.exception.ItemNotFoundException
import com.tuple.inventory.model.Item
import com.tuple.inventory.repository.ItemRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import  org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ItemService( private val itemRepository : ItemRepository) {
    fun getAllItem(pageNumber:Int,pageSize:Int): Page<Item> {
        val pageable:Pageable = PageRequest.of(pageNumber,pageSize)
        return itemRepository.findAll(pageable)
    }

    fun findItemByItemId(itemId:String): Item? {
        val existItem = itemId.let { itemRepository.findById(it) }
        if(existItem.isEmpty)
            throw ItemNotFoundException("Item Not Found with itemId :$itemId")
        return existItem.get()
    }

    fun createItem(item: Item):Item{
        val existItem = item.itemId.let { itemRepository.existsById(it) }
        if(existItem){
            throw ItemNotFoundException("Item exist with itemId:${item.itemId}")
        }
        return itemRepository.save(item)
    }

    fun deleteItem(itemId:String):String{
        val existItem = itemId.let { itemRepository.existsById(it)}
        if (!existItem)
            throw ItemNotFoundException("Item Not Found with itemId:${itemId}")
        itemRepository.deleteById(itemId)
        return "Item with itemId: $itemId is deleted!"
    }

     fun updateItem(itemId:String, item: Item): Item? {
       val existItem = item.itemId.let { itemRepository.findById(it)}
        if (existItem .isEmpty) {
            throw ItemNotFoundException("Item Not Found with itemId:${item.itemId}")
        };
        val getExistItem = existItem.get()
        getExistItem.itemName= item.itemName
        getExistItem.itemDescription= item.itemDescription
        getExistItem.itemType= item.itemType
        getExistItem.itemCategory= item.itemCategory
        getExistItem.price= item.price
        getExistItem.status= item.status
        getExistItem.pickUpAllowed = item.pickUpAllowed
        getExistItem.shippingAllowed = item.shippingAllowed
        getExistItem.deliveryAllowed  = item.deliveryAllowed
        itemRepository.save(getExistItem)
        return existItem.get()
    }

    fun isItemExist(itemId: String): Boolean {
        return  itemRepository.existsById(itemId)
    }

}