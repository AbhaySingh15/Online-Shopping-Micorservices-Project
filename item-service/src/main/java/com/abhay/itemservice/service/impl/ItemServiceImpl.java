package com.abhay.itemservice.service.impl;

import com.abhay.itemservice.entity.Item;
import com.abhay.itemservice.repository.ItemRepository;
import com.abhay.itemservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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
        for(String itemName:name){
            Item item = itemRepo.findByName(itemName);
            listOfItems.add(item);
        }
        return listOfItems;
    }
}
