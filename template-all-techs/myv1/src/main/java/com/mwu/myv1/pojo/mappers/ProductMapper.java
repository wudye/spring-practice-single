package com.mwu.myv1.pojo.mappers;


import com.mwu.myv1.model.mysql.ProductEntity;
import com.mwu.myv1.pojo.Product;
import com.mwu.myv1.repository.mysql.dto.ProductDetailVO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ProductMapper {

    //  @Mappings({})
    ProductEntity toEntity(Product product);

    //  @Mappings({})
    Product toModel(ProductEntity entity);

    Product toModelV2(ProductDetailVO vo);
}
