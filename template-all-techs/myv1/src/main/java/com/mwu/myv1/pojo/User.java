package com.mwu.myv1.pojo;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private Long userId;
    private String username;
    private String encryptedPassword;
    private Boolean enabled;

    private List<Role> roles;
}
