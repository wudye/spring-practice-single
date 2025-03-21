package com.mwu.myv1.config.auth.service;

import com.mwu.myv1.config.auth.CustomUserDetails;
import com.mwu.myv1.repository.UserAdapter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultUserService implements UserService {
    private final UserAdapter userAdapter;

    public DefaultUserService(UserAdapter userAdapter) {
        this.userAdapter = userAdapter;
    }

    @Override
    public CustomUserDetails getUserDetailByUserId(Long userId) {
        return userAdapter.getUserDetailByUserId(userId);
    }

    @Override
    public CustomUserDetails getUserDetailByUsername(String username) {
        return userAdapter.getUserDetailByUsername(username);
    }

    @Override
    public CustomUserDetails loadDefaultUserForFixedTokenAuth() {
        return CustomUserDetails.builder().userId(0L).username("default").roles(List.of("USER")).build();
    }


    /** Use only for Unit Test common cases */
    public void voidMethodForUnitTesting() {
        String s = feature2();
    }

    /** Use only for Unit Test common cases */
    public void voidMethodForUnitTesting2() {
    }

    /** Use only for Unit Test common cases */
    public void feature1() {
        voidMethodForUnitTesting();
        voidMethodForUnitTesting2();
        feature2();
    }

    /** Use only for Unit Test common cases */
    public String feature2() {
        return "";
    }
}
