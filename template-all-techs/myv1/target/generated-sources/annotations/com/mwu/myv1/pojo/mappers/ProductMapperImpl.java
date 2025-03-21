package com.mwu.myv1.pojo.mappers;

import com.mwu.myv1.model.mysql.ProductEntity;
import com.mwu.myv1.pojo.Product;
import com.mwu.myv1.repository.mysql.dto.ProductDetailVO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-20T19:23:11+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductEntity toEntity(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();

        productEntity.setId( product.getId() );
        productEntity.setName( product.getName() );
        productEntity.setCategoryId( product.getCategoryId() );
        productEntity.setPrice( product.getPrice() );
        productEntity.setStatus( product.getStatus() );
        productEntity.setCreatedAt( product.getCreatedAt() );

        return productEntity;
    }

    @Override
    public Product toModel(ProductEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.id( entity.getId() );
        product.name( entity.getName() );
        product.categoryId( entity.getCategoryId() );
        product.status( entity.getStatus() );
        product.price( entity.getPrice() );
        product.createdAt( entity.getCreatedAt() );

        return product.build();
    }

    @Override
    public Product toModelV2(ProductDetailVO vo) {
        if ( vo == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        if ( vo.getId() != null ) {
            product.id( vo.getId().longValue() );
        }
        product.name( vo.getName() );
        product.price( vo.getPrice() );
        product.quantity( vo.getQuantity() );
        product.imagePath( vo.getImagePath() );

        return product.build();
    }
}
