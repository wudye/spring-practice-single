package com.mwu.myv1.pojo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
public class Post {
    private Integer id;
    private Integer userId;
    private String title;
    private String body;
}
