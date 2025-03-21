package com.mwu.myv1.config.database.mysql;


import jakarta.persistence.EntityManagerFactory;
import org.hibernate.annotations.Bag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "RoDemoEntityManagerFactory",
        transactionManagerRef = "RoDemoTransactionManager",
        basePackages = {"com.mwu.myv1.repository.mysql.readonly"}
)
public class RoDemoDataSourceConfig {
    public static final String PERSISTENCE_UNIT_NAME = "RoDemo";
    public static final String MODEL_PACKAGE = "com.mwu.myv1.model.mysql";

    @Bean(name = "RoDemoDataSource")
    @ConfigurationProperties("spring.datasource.demo-ro")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "RoDemoEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean roDemoEntityManagerFactory(
            EntityManagerFactoryBuilder entityManagerFactoryBuilder,
            @Qualifier("RoDemoDataSource") DataSource dataSource) {

        return entityManagerFactoryBuilder
                .dataSource(dataSource)
                .packages(MODEL_PACKAGE)
                .properties(JpaAdditionalPropertiesHelper.additionalProperties())
                .persistenceUnit(PERSISTENCE_UNIT_NAME)
                .build();
    }

    @Bean(name = "RoDemoTransactionManager")
    public PlatformTransactionManager roDemoTransactionManager(
            @Qualifier("RoDemoEntityManagerFactory") EntityManagerFactory dfEntityManagerFactory) {
        return new JpaTransactionManager(dfEntityManagerFactory);
    }


}
