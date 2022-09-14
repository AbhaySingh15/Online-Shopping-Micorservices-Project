package com.abhay.itemservice.service.impl;

import com.abhay.itemservice.entity.Item;
import com.abhay.itemservice.repository.ItemRepository;
import com.abhay.itemservice.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepo;

    @Override
    public List<Item> getAllItems() {
       return itemRepo.getAllItems();
    }

    @Override
    public List<Item> getItemsByName(String... name) {
        List<Item> listOfItems = new ArrayList<>();
        for(String itemName: name){
            Item item = itemRepo.findByName(itemName);
            if(item==null){
                log.info("item name: "+itemName+" is not in the stock");
                continue;
            }
            listOfItems.add(item);
        }
        return listOfItems;
    }

    @Override
    public Item addItem(Item item) {
        return itemRepo.save(item);
    }
}
