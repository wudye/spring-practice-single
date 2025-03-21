package com.mwu.myv1.utils.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.mwu.myv1.pojo.Post;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponse extends BaseResponse {
    private Post post;
}
