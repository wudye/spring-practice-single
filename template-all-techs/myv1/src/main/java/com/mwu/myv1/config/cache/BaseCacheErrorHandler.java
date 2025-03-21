package com.mwu.myv1.config.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;
@Slf4j
public class BaseCacheErrorHandler implements CacheErrorHandler {
    @Override
    public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
        handleCacheException(exception);
        log.error("Cache error" + cache.getName(), exception);

    }

    private void handleCacheException(RuntimeException exception) {
    }

    @Override
    public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
        handleCacheException(exception);

        log.error("Cache error" + cache.getName(), exception);

    }

    @Override
    public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
        handleCacheException(exception);
        log.error("Cache error" + cache.getName(), exception);

    }

    @Override
    public void handleCacheClearError(RuntimeException exception, Cache cache) {
        handleCacheException(exception);
        log.error("Cache error" + cache.getName(), exception);

    }
}
