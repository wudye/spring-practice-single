package com.mwu.myv1.componetConfig.locks;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component("ProductRedisLocker")
public class ProductRedisLocker extends BaseRedisLocker<Long> {

    @PostConstruct
    public void init() {
        setTtl(10);
        setKeyType("product");
        setBucketKey("processing_products");
        initBucket();
    }
}