package com.abhay.itemservice.controller;

import com.abhay.itemservice.entity.Item;
import com.abhay.itemservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/items")
    public List<Item> getAllItems(){
        return itemService.getAllItems();
    }

    @GetMapping("/item")
    public List<Item> findItemsByName(@RequestParam String... name){
        return itemService.getItemsByName(name);
    }

    @PostMapping("/addItem")
    public ResponseEntity<Item> addItem(@RequestBody @Valid Item item){
        return ResponseEntity.ok(itemService.addItem(item));
    }
}
