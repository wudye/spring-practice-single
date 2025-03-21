package com.mwu.orderservice.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter

public class Product implements Serializable {

    private String id;
    private String name;
    private double price;
}
