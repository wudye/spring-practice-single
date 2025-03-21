package com.mwu.myv1.config.database.mysql;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "DemoEntityManagerFactory",
        transactionManagerRef = "DemoTransactionManager",
        basePackages = {"com.mwu.myv1.repository.mysql.primary"}
)
public class DemoDataSourceConfig {

    public static final String PERSISTENCE_UNIT_NAME = "Demo";
    public static final String MODEL_PACKAGE = "com.mwu.myv1.model.mysql";
    @Bean(name = "DemoDataSource")
    @Primary
    @ConfigurationProperties("spring.datasource.demo")
    public DataSource demoDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "DemoEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean demoEntityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("DemoDataSource") DataSource demoDataSource) {

        return builder
                .dataSource(demoDataSource)
                .packages(MODEL_PACKAGE)

                .properties(JpaAdditionalPropertiesHelper.additionalProperties())

                .persistenceUnit(PERSISTENCE_UNIT_NAME)
                .build();


    }

    @Bean(name = "DemoTransactionManager")
    @Primary
    public PlatformTransactionManager demoTransactionManager(
            @Qualifier("DemoEntityManagerFactory") EntityManagerFactory demoEntityManagerFactory) {
        return new JpaTransactionManager(demoEntityManagerFactory);
    }

}
