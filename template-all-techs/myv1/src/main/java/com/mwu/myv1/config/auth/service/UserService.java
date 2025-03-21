package com.mwu.myv1.config.auth.service;

import com.mwu.myv1.config.auth.CustomUserDetails;
import org.springframework.stereotype.Component;

@Component
public interface UserService {

    CustomUserDetails getUserDetailByUserId(Long userId);

    CustomUserDetails getUserDetailByUsername(String username);

    CustomUserDetails loadDefaultUserForFixedTokenAuth();


}
