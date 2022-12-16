package com.abhay.itemservice.service;

import com.abhay.itemservice.entity.Item;
import java.util.List;

public interface ItemService {

    List<Item> getAllItems();

    List<Item> getItemsByName(String... name);

    Item addItem(Item item);

    Item getItemByName(String name);
}
