package com.example.demo.service;

import com.example.demo.modal.Item;

import java.util.List;

public interface ItemService {
    List<Item> findByName(String itemName);

    List<Item> findByCategory(String category);

    List<Item> findByPriceBetween(double low, double high);
}
