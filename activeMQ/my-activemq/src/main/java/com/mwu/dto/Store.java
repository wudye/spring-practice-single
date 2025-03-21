package com.mwu.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Store{

    private int itemId;
    private String itemName;
    private int itemQuantity;
}