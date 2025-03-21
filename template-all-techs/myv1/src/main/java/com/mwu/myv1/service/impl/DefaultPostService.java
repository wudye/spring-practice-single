package com.mwu.myv1.service.impl;

import com.mwu.myv1.componetConfig.activeMq.ActiveMQProducer;
import com.mwu.myv1.componetConfig.activeMq.DemoMessage;
import com.mwu.myv1.componetConfig.feignClient.DemoRestClient;
import com.mwu.myv1.exception.ActiveMQPublishException;
import com.mwu.myv1.exception.PostNotFoundException;
import com.mwu.myv1.exception.ServiceUnavailableException;
import com.mwu.myv1.pojo.Post;
import com.mwu.myv1.service.PostService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component("PostService")
@RequiredArgsConstructor
@Slf4j
public class DefaultPostService implements PostService {
    private final DemoRestClient demoRestClient;
    private final ActiveMQProducer activeMQProducer;

    @Override
    @Retryable(
            retryFor = PostNotFoundException.class,
            maxAttemptsExpression = "${retry.maxAttempts}",
            backoff = @Backoff(delayExpression = "${retry.backoffDelay}")
    )
    @CircuitBreaker(name = "demo-feign-client", fallbackMethod = "getLastPostCircuitFallback")
    public Post getLastPost() throws PostNotFoundException {
        Post post = demoRestClient.getPostById(100L); // just for demo =)))
        if (post == null) {
            log.info("No Posts Found");
            throw new PostNotFoundException();
        }
        return post;
    }
    /**
     * We should declare the fallback at Service layer to maintain better separation of concerns.
     */
    public Post getLastPostCircuitFallback(Exception e) throws Exception {
        // for each exception, we can have a diff scenario
        if (e instanceof PostNotFoundException) throw e;
        log.error("Post Service is unavailable", e);
        throw new ServiceUnavailableException("Post Service is unavailable");
    }

    @Override
    public void testActiveMQ(String queueName) {
        try{
            activeMQProducer.send(
                    queueName, DemoMessage.builder().id(10L).created(LocalDateTime.now()).build());

        } catch (ActiveMQPublishException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
