package com.mwu.myv1.repository;

import com.mwu.myv1.exception.ProductNotFoundException;
import com.mwu.myv1.pojo.PriceCheckResult;
import com.mwu.myv1.pojo.Product;
import com.mwu.myv1.utils.request.GetAllProductRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ProductAdapter {
    List<Product> loadAllProducts(Pageable paging);

    List<Product> loadAllProductByFilter(GetAllProductRequest getAllProductRequest);

    void insertNewProduct(Product product);

    void updateNewProduct(Product product);

    Product loadProductDetails(Long id) throws ProductNotFoundException;

    CompletableFuture<PriceCheckResult> checkAsyncPrice(Product product) throws InterruptedException;

    PriceCheckResult checkPrice(Product product) throws InterruptedException;

    void updatePrice(long productId, float price);

    Product findById(long productId);
}
