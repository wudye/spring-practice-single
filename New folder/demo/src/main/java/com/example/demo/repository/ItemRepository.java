package com.example.demo.repository;

import com.example.demo.modal.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ItemRepository extends ElasticsearchRepository<Item, Integer> {
    List<Item> findByName(String name);

    List<Item> findByCategory(String category);

    List<Item> findByPriceBetween(double low, double high);
}
