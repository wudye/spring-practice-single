package com.mwu.myv1.config.database.mongoDB;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mapping.model.SnakeCaseFieldNamingStrategy;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.*;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.mwu.myv1.repository.mongoDB")
@EnableMongoAuditing // The @EnableMongoAuditing annotation is used to enable auditing features for MongoDB, such as automatically populating fields like created and modified dates.
public class DemoMongoConfig {

    @Primary
    @Bean(name = "primary")
    @ConfigurationProperties(prefix = "spring.data.mongodb")
    public MongoProperties getDemoProperties() {
        return new MongoProperties();
    }

    @Primary
    @Bean(name = "mongoTemplate")
    public MongoTemplate mongoTemplate() {
        MongoDatabaseFactory mongoDatabaseFactory =
                demoFactory(getDemoProperties());
        return new MongoTemplate(mongoDatabaseFactory, mongoConverter(mongoDatabaseFactory));

    }

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

    private MongoConverter mongoConverter(MongoDatabaseFactory factory) {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);

        MappingMongoConverter mongoConverter =
                new MappingMongoConverter(dbRefResolver, initMappingContext());
        mongoConverter.setTypeMapper(new DefaultMongoTypeMapper(null));

        mongoConverter.afterPropertiesSet();
        return mongoConverter;

    }
}
