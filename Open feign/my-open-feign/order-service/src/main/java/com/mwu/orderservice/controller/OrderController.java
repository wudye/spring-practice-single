package com.mwu.orderservice.controller;


import com.mwu.orderservice.model.Order;
import com.mwu.orderservice.model.OrderRequest;
import com.mwu.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class OrderController{

    private final OrderService orderService;

    @PostMapping("/create-order")
    public Order placeOrder(@RequestBody OrderRequest request){
        Order response = orderService.createOrder(request);
        return response;
    }
}