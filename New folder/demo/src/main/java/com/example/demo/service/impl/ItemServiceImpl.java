package com.example.demo.service.impl;

import com.example.demo.modal.Item;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    @Override
    public List<Item> findByName(String itemName) {
        return itemRepository.findByName(itemName);
    }

    @Override
    public List<Item> findByCategory(String category) {
        return itemRepository.findByCategory(category);
    }

    @Override
    public List<Item> findByPriceBetween(double low, double high) {
        return itemRepository.findByPriceBetween(low, high);
    }
}
