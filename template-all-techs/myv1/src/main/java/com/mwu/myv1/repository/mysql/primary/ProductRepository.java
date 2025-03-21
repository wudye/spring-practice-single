package com.mwu.myv1.repository.mysql.primary;

import com.mwu.myv1.model.mysql.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Modifying
    @Query(value = "update products set price = :price where id = :productId", nativeQuery = true)
    void updatePriceById(@Param("productId") long productId, @Param("price") float price);
}

