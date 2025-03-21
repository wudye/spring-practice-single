package com.mwu.myv1.componetConfig.locks;

import com.mwu.myv1.exception.ConcurrentProcessingException;
import com.mwu.myv1.model.mongoDB.ProcessingProduct;
import com.mwu.myv1.repository.mongoDB.ProcessingProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMongoLocker {

    private final ProcessingProductRepository processingProductRepository;

    public void lock(Long id) throws ConcurrentProcessingException {
        try {
            processingProductRepository.save(ProcessingProduct.builder().productId(id).build());
        } catch (DuplicateKeyException e) {
            throw new ConcurrentProcessingException("product " + id + " is processing by others");
        }
    }

    public void unlock(Long productId) {
        processingProductRepository.deleteByProductId(productId);
    }
}
