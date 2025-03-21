package com.example.demo.config;


import com.example.demo.modal.Item;
import com.example.demo.repository.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataSetup {
    private final ItemRepository itemRepository;
    private final CSVParser csvParser;
    @PostConstruct
    public void setupData() {
        List<Item> itemList = csvParser.csvParser("item.csv");
        itemRepository.saveAll(itemList);
    }
}