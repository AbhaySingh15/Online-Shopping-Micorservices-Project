package com.abhay.salesorderservice.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ItemList {
    private List<Item> itemList;
    public ItemList(){
        itemList = new ArrayList<>();
    }
}
