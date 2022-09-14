package com.abhay.itemservice.service;

import com.abhay.itemservice.entity.Item;
import com.abhay.itemservice.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ItemService {

    List<Item> getAllItems();

    List<Item> getItemsByName(String... name);

    Item addItem(Item item);
}
