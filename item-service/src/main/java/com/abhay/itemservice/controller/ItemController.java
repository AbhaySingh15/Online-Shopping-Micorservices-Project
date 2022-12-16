package com.abhay.itemservice.controller;

import com.abhay.itemservice.entity.Item;
import com.abhay.itemservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/items")
    public List<Item> getAllItems(){
        var itemList = itemService.getAllItems();
        for(Item item: itemList){
            Link link = linkTo(methodOn(ItemController.class).findItemByName(item.getName())).withSelfRel();
            item.add(link);
        }
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

    @GetMapping("/item/{name}")
    public Item findItemByName(@PathVariable("name") String name){
        return itemService.getItemByName(name);
    }
}
