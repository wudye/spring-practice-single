package com.mwu.myv1.repository;

import com.mwu.myv1.config.auth.CustomUserDetails;

public interface UserAdapter {
    CustomUserDetails getUserDetailByUserId(Long userId);

    CustomUserDetails getUserDetailByUsername(String username);
}
