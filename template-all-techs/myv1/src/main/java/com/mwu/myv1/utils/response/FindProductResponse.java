package com.mwu.myv1.utils.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mwu.myv1.pojo.Product;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FindProductResponse extends BaseResponse {
    private Product product;
}
