package com.mwu.myv1.repository;

import com.mwu.myv1.config.auth.CustomUserDetails;
import com.mwu.myv1.config.cache.CacheConfig;
import com.mwu.myv1.utils.EncryptedPasswordUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DefaultUserAdapter implements UserAdapter {


    @Override
    @Cacheable(
            cacheNames = "getUserDetailByUserId",
            unless = "#result == null",
            cacheManager = CacheConfig.CACHE_REDIS)
    public CustomUserDetails getUserDetailByUserId(Long userId) {
        return CustomUserDetails.builder()
                .userId(123L)
                .username("hieupd")
                .encryptedPassword(EncryptedPasswordUtils.encryptPassword("test"))
                .departmentId(1)
                .tel("0364139622")
                .roles(List.of("USER", "DBA"))
                .build();
    }

    @Override
    @Cacheable(
            cacheNames = "getUserDetailByUsername",
            unless = "#result == null",
            cacheManager = CacheConfig.CACHE_REDIS)
    public CustomUserDetails getUserDetailByUsername(String username) {
        // TODO: get from database
        return CustomUserDetails.builder()
                .userId(123L)
                .username("hieupd")
                .encryptedPassword(EncryptedPasswordUtils.encryptPassword("test"))
                .departmentId(1)
                .tel("0364139622")
                .build();
    }
}
