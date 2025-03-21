package com.mwu.productservce.controller;

import com.mwu.productservce.model.Product;
import com.mwu.productservce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class ProductController{

    private final ProductService productService;

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id")String id){
        Product product = productService.getProductById(id);
        return product;
    }
}