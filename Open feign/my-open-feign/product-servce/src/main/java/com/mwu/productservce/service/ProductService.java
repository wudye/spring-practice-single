package com.mwu.productservce.service;

import com.mwu.productservce.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    public Product getProductById(String id) {
        return new Product("1", "MILK", 10.0);
    }
}
