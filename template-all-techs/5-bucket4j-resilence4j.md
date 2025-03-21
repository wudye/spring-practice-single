API Rate Limiting
It restricts the number of API calls that a client can make within a certain time frame. This helps defend the API against overuse, both unintentional and malicious.

Rate limits are often applied to an API by tracking the IP address, or in a more business-specific way, such as API keys or access tokens. As API developers, we have several options when a client reaches the limit:

Queueing the request until the remaining time period has elapsed
Allowing the request immediately, but charging extra for this request
Rejecting the request (HTTP 429 Too Many Requests)

Bucket4j
 is a thread-safe library that can be used in either a standalone JVM application, or a clustered environment. It also supports in-memory or distributed caching via the JCache (JSR107) specification.

 The Bucket interface represents the token bucket with a maximum capacity. It provides methods such as tryConsume and tryConsumeAndReturnRemaining for consuming tokens. These methods return the result of consumption as true if the request conforms with the limits, and the token was consumed.

The Bandwidth class is the key building block of a bucket, as it defines the limits of the bucket. We use Bandwidth to configure the capacity of the bucket and the rate of refill.

The Refill class is used to define the fixed rate at which tokens are added to the bucket. We can configure the rate as the number of tokens that would be added in a given time period. For example, 10 buckets per second or 200 tokens per 5 minutes, and so on.
The tryConsumeAndReturnRemaining method in Bucket returns ConsumptionProbe. ConsumptionProbe contains, along with the result of consumption, the status of the bucket, such as the tokens remaining, or the time remaining until the requested tokens are available in the bucket again.
easy way in https://www.geeksforgeeks.org/rate-limiting-a-spring-api-using-bucket4j/


Main Patterns in Resilience4j
Circuit Breaker
Retry
Rate Limiter
Retry
Bulkhead
Time Limiter


Circuit Breaker
    When a server application repeatedly responds with errors or responds too slowly, the circuit breaker on the client side detects this and temporarily stops sending requests (opens). During this time, instead of sending requests, it returns a standard response (fallback) or throws an exception. This approach gives the server application some time to recover without being overwhelmed by additional requests. This is especially useful in high-load systems where thousands of requests may be sent per second.
    The Circuit Breaker has three main states:

    CLOSED: The application behaves normally, and all requests are sent as usual.
    OPEN: The request chain is interrupted, and no requests are sent.
    HALF_OPEN: After a timeout, the circuit breaker moves from OPEN to HALF_OPEN, sending a limited number of test requests to the server. If the server responds successfully, the circuit breaker returns to CLOSED; otherwise, it goes back to OPEN.
    And, two special states:

    DISABLED: The circuit breaker is turned off.
    FORCED_OPEN: The circuit breaker is forcibly set to OPEN.


https://medium.com/@alxkm/building-resilient-spring-boot-applications-with-resilience4j-cf8e5e7c2700
https://medium.com/garantibbva-teknoloji/applying-the-five-most-used-resiliency-patterns-using-resilience4j-with-spring-boot-1cc695988d