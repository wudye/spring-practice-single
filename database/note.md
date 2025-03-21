Choose Lettuce for projects needing high performance, concurrency, and scalability. Choose Jedis for simpler setups that benefit from reliable, easy-to-implement Redis connectivity

Redis can be used with RedisTemplate and save the data in the key-value pair
use Redis with CrudRepository means we can write the queries for the result in Redis like JPA with Spring Boot

spring.data.redis.database=0
spring.data.redis.host=localhost
spring.data.redis.port=6379
spring.data.redis.username=user
spring.data.redis.password=secret

if use Lettuce + CrudRepository
do not need config redis
but config model
@RedisHash(value = "talename") 
if use Lettuce + RedisTemplate
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {

        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        // Add some specific configuration such as key serializers, etc.
        return template;
    }
jedis need config redis and add dependecy

        <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
        </dependency>



Spring Data Redis uses commons-pool2 library to configure connection pooling. So, we need first to add the below dependency in our pom.xml file.
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-pool2</artifactId>
</dependency>

common.redis.pool-config.maxIdle=64
common.redis.pool-config.maxTotal=64
common.redis.pool-config.minIdle=8

Spring Boot provides a set of annotations to simplify caching:

@Cacheable: Caches the result of a method based on its parameters.
@CachePut: Updates the cache with the method’s result.
@CacheEvict: Removes data from the cache.
@Caching: Allows combining multiple caching annotations using a single method.

Cache: Many applications struggle with the need to store and retrieve data quickly, especially in systems with high latency. Due to its speed, Redis is the ideal choice for caching API calls, session states, complex computations, and database queries.