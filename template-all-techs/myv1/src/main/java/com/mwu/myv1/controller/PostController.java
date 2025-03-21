package com.mwu.myv1.controller;

import com.mwu.myv1.constant.AppConstants;
import com.mwu.myv1.exception.PostNotFoundException;
import com.mwu.myv1.exception.RateLimitExceededException;
import com.mwu.myv1.exception.ServiceUnavailableException;
import com.mwu.myv1.pojo.Post;
import com.mwu.myv1.service.PostService;
import com.mwu.myv1.utils.response.PostResponse;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
@Tag(name = "Post Controller")
@RequiredArgsConstructor
@Slf4j
public class PostController extends BaseController {

    private final PostService postService;

    @GetMapping(value = "/get-last")
    @Parameter(
            name = AppConstants.FIXED_TOKEN_HEADER,
            required = true,
            in = ParameterIn.HEADER,
            example = "39489c18-7b74-11ec-90d6-0242ac120003")
    @RateLimiter(name = "getLastPost", fallbackMethod = "getLastPostRateLimitFallback")
    public ResponseEntity<PostResponse> getLastPost() throws PostNotFoundException {
        try {
            Post post = postService.getLastPost();
            return new ResponseEntity<>(
                    PostResponse.builder().post(post).success(true).build(), HttpStatus.OK);
        } catch (ServiceUnavailableException e) {
            // I catch it here to maintain the same response format,
            // else it will respond the default string at ControllerAdvice
            // Notes: We should define the global response format for each status codes, and align with clients
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(PostResponse.builder().message("Service is unavailable").success(false).build());
        }
    }

    private ResponseEntity<PostResponse> getLastPostRateLimitFallback() throws PostNotFoundException {
        log.error("getLastPostRateLimitFallback");
        throw new RateLimitExceededException("Rate limit exceeded. Please try again later.");

    }

    @GetMapping(value = "/test-activemq")
    @Parameter(
            name = AppConstants.FIXED_TOKEN_HEADER,
            required = true,
            in = ParameterIn.HEADER,
            example = "39489c18-7b74-11ec-90d6-0242ac120003")
    public PostResponse testActiveMQ() {
        postService.testActiveMQ("demo-queue");
        return PostResponse.builder().success(true).build();
    }

}
