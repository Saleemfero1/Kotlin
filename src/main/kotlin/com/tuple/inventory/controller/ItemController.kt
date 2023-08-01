package com.tuple.inventory.controller

import com.tuple.inventory.model.Item
import com.tuple.inventory.service.ItemService
import org.springframework.boot.context.properties.bind.DefaultValue
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
@CrossOrigin("*")
@RestController
@RequestMapping("/item")
class ItemController( private val itemService: ItemService?=null) {
    @GetMapping("/")
    fun getItems(@RequestParam(value = "pageSize",defaultValue = "5", required = false)pageSize:Int,
                 @RequestParam(value = "pageNumber",defaultValue = "0", required = false)pageNumber: Int):ResponseEntity<Page<Item>>{
        return ResponseEntity(itemService?.getAllItem(pageNumber,pageSize),HttpStatus.OK);
    }
    @GetMapping("/{itemId}")
    fun getItems(@PathVariable("itemId") itemId:String):ResponseEntity<Item>{
        return ResponseEntity(itemService?.findItemByItemId(itemId),HttpStatus.OK);
    }

    @PostMapping("/")
    fun createItem(@RequestBody item: Item): ResponseEntity<Item> {
        return ResponseEntity(itemService?.createItem(item), HttpStatus.CREATED)
    }
    @DeleteMapping("/{itemId}")
    fun deleteItem(@PathVariable("itemId") itemId:String): ResponseEntity<String> {
        return ResponseEntity(itemService?.deleteItem(itemId), HttpStatus.ACCEPTED)
    }

    @PutMapping("/{itemId}")
    fun updateItem(@PathVariable("itemId") itemId:String,@RequestBody item: Item):ResponseEntity<Item>{
        return ResponseEntity(itemService?.updateItem(itemId,item),HttpStatus.OK)
    }


}
/*
method two using @get
  @get:GetMapping("/")
    val account: ResponseEntity<List<Account?>>
        get() = ResponseEntity(accountRepo!!.findAll(), HttpStatus.OK)
* */