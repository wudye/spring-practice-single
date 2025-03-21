mongoDB:
    @Configuration
    @EnableMongoRepositories(basePackages = "org.spring.mongo.demo")
    @EnableMongoAuditing 
        // The @EnableMongoAuditing annotation is used to enable auditing features for MongoDB, such as automatically populating fields like created and modified dates.

public class DemoMongoConfig {


    // This method is configured to read properties prefixed with spring.data.mongodb from the application's configuration files.
    @Primary
    @Bean(name = "primary")
    @ConfigurationProperties(prefix = "spring.data.mongodb")
    public MongoProperties getDemoProperties() {
        return new MongoProperties();
    }

    // This method creates a MongoDatabaseFactory using the demoFactory method and a MongoConverter using the mongoConverter method.
    @Primary
    @Bean(name = "mongoTemplate")
    public MongoTemplate mongoTemplate() {
        MongoDatabaseFactory mongoDatabaseFactory =
                demoFactory(getDemoProperties());
        return new MongoTemplate(mongoDatabaseFactory, mongoConverter(mongoDatabaseFactory));

    }

    //  It uses the URI from the MongoProperties to create a SimpleMongoClientDatabaseFactory.
    @Primary
    @Bean
    public MongoDatabaseFactory demoFactory(MongoProperties properties) {
        return new SimpleMongoClientDatabaseFactory(properties.getUri());
    }

    private MongoMappingContext initMappingContext() {
        MongoMappingContext mappingContext = new MongoMappingContext();
        mappingContext.setFieldNamingStrategy(new SnakeCaseFieldNamingStrategy());
        return mappingContext;
    }

    // The mongoConverter method creates a MongoConverter using a DefaultDbRefResolver and the MongoMappingContext initialized by initMappingContext. It also sets a DefaultMongoTypeMapper with a null value to avoid saving _class attributes in MongoDB documents.
    private MongoConverter mongoConverter(MongoDatabaseFactory factory) {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);

        MappingMongoConverter mongoConverter =
                new MappingMongoConverter(dbRefResolver, initMappingContext());
        mongoConverter.setTypeMapper(new DefaultMongoTypeMapper(null));

        mongoConverter.afterPropertiesSet();
        return mongoConverter;

    }
}

second config
https://hevodata.com/learn/spring-boot-mongodb-config/
@Configuration
@EnableMongoRepositories(basePackages = "org.spring.mongo.demo")
public class MongoConfig extends AbstractMongoClientConfiguration {

    private final List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();

    @Override
    protected String getDatabaseName() {
        return "test";
    }

    @Override
    public MongoClient mongoClient() {
        final ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/test");
        final MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build();
        return MongoClients.create(mongoClientSettings);
    }

    @Override
    public Collection<String> getMappingBasePackages() {
        return Collections.singleton("org.spring.mongo.demo");
    }

}


cache-> in redis + in local( Caffeine)
Caffeine cache is a high-performance cache library for Java. I

Time To Live(TTL)

in main funciton
@EnableCaching

redis
@Configuration
public class CacheConfig  extends CachingConfigurerSupport implements CachingConfigurer {

    public static final String CACHE_REDIS = "CACHE_REDIS";
    public static final String CACHE_LOCAL = "CACHE_LOCAL";
   //  This value specifies that the entries in the Redis cache should expire after 60 seconds.
    private final int CACHE_REDIS_TTL = 60;
   //  This value indicates that the entries in the local cache should expire after 120 seconds.
    private final int CACHE_LOCAL_TTL = 120;


    //  It disables caching of null values and uses a GenericJackson2JsonRedisSerializer for value serialization.
    public RedisCacheConfiguration buildCacheConfig(int seconds) {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(seconds))
                .disableCachingNullValues()
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(
                                new GenericJackson2JsonRedisSerializer()));

    }

    // This writer is configured to avoid locking during cache writes
    @Bean
    public RedisCacheWriter redisCacheWriter(RedisConnectionFactory redisConnectionFactory) {
        return RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
    }


    @Bean(name = CACHE_REDIS)
    @Primary
    public RedisCacheManager redisCacheManager(RedisCacheWriter redisCacheWriter) {
        return new RedisCacheManager(redisCacheWriter, buildCacheConfig(CACHE_REDIS_TTL));
    }


//    @Bean
//    public CacheProperties.Caffeine caffeineConfig() {
//        return Caffeine.newBuilder().expireAfterWrite(60, TimeUnit.MINUTES);
//    }
    @Bean(name = CACHE_LOCAL)
    public CacheManager localCacheManager() {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(
                Caffeine.newBuilder()
                        .initialCapacity(50)
                        .maximumSize(3000)
                        .expireAfterWrite(CACHE_LOCAL_TTL, TimeUnit.SECONDS));
        return caffeineCacheManager;
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return new BaseCacheErrorHandler();
    }

}

after config use annotation to handle cache