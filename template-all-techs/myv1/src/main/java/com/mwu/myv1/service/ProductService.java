package com.mwu.myv1.service;
import com.mwu.myv1.exception.EmptyRequestException;
import com.mwu.myv1.exception.ProductNotFoundException;
import com.mwu.myv1.pojo.PriceCheckResult;
import com.mwu.myv1.pojo.Product;
import com.mwu.myv1.utils.request.*;
import com.mwu.myv1.utils.response.UpdatePriceResult;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface ProductService {

    void insertNewProduct(NewProductRequest request);

    void updateNewProduct(UpdateProductRequest request) throws ProductNotFoundException;

    List<Product> loadAllProducts(Integer pageNo, Integer pageSize, String sortBy);

    List<Product> loadAllProductByFilter(GetAllProductRequest request);

    Product loadProductDetails(Long id) throws ProductNotFoundException;

    List<PriceCheckResult> checkAsyncPrice(PriceCheckRequest request)
            throws EmptyRequestException, ExecutionException, InterruptedException;

    List<PriceCheckResult> checkPrice(PriceCheckRequest request)
            throws EmptyRequestException, InterruptedException;

    UpdatePriceResult updatePrice(UpdatePriceRequest request);
}
