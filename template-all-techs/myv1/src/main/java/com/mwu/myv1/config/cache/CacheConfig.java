package com.mwu.myv1.config.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig  extends CachingConfigurerSupport implements CachingConfigurer {

    public static final String CACHE_REDIS = "CACHE_REDIS";
    public static final String CACHE_LOCAL = "CACHE_LOCAL";
   //  This value specifies that the entries in the Redis cache should expire after 60 seconds.
    private final int CACHE_REDIS_TTL = 60;
   //  This value indicates that the entries in the local cache should expire after 120 seconds.
    private final int CACHE_LOCAL_TTL = 120;


    public RedisCacheConfiguration buildCacheConfig(int seconds) {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(seconds))
                .disableCachingNullValues()
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(
                                new GenericJackson2JsonRedisSerializer()));

    }

    @Bean
    public RedisCacheWriter redisCacheWriter(RedisConnectionFactory redisConnectionFactory) {
        return RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
    }


    @Bean(name = CACHE_REDIS)
    @Primary
    public RedisCacheManager redisCacheManager(RedisCacheWriter redisCacheWriter) {
        return new RedisCacheManager(redisCacheWriter, buildCacheConfig(CACHE_REDIS_TTL));
    }


//    @Bean
//    public CacheProperties.Caffeine caffeineConfig() {
//        return Caffeine.newBuilder().expireAfterWrite(60, TimeUnit.MINUTES);
//    }
    @Bean(name = CACHE_LOCAL)
    public CacheManager localCacheManager() {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(
                Caffeine.newBuilder()
                        .initialCapacity(50)
                        .maximumSize(3000)
                        .expireAfterWrite(CACHE_LOCAL_TTL, TimeUnit.SECONDS));
        return caffeineCacheManager;
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return new BaseCacheErrorHandler();
    }

}
