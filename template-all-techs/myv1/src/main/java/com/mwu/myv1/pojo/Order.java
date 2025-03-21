package com.mwu.myv1.pojo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Order {
    private Integer id;
    private Integer userId;
    private LocalDateTime created;
}
