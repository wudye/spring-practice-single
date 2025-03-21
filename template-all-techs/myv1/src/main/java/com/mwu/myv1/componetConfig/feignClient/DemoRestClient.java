package com.mwu.myv1.componetConfig.feignClient;

import com.mwu.myv1.config.feign.PostFeignConfig;
import com.mwu.myv1.exception.PostNotFoundException;
import com.mwu.myv1.pojo.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(
        name ="demo-rest-client",
        url = "${jsonplaceholder-api-host}",
        configuration = PostFeignConfig.class)
public interface DemoRestClient {

    @RequestMapping(method = RequestMethod.GET, value = "${jsonplaceholder-api-path}")
    List<Post> getLastPost();

    @RequestMapping(
            method = RequestMethod.GET,
            value = "${jsonplaceholder-api-path}/{postId}",
            headers = {"${jsonplaceholder-api-token-header}=${jsonplaceholder-api-key}"},
            produces = "application/json")
    Post getPostById(@PathVariable("postId") Long postId);

}
