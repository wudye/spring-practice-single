package com.mwu.myv1.repository.mysql.dto;

import java.time.LocalDateTime;

public interface ProductDetailVO {

    Integer getId();

    String getName();

    Float getPrice();

    Integer getQuantity();

    String getImagePath();

    LocalDateTime getCreated();
}