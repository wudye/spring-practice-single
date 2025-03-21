use undertow replace tomcat



config tow mysql databases:
    name it wih different name like first, second in yml file
    config it seprately 
    @Configuration
    @EnableTransactionManagement
    @EnableJpaRepositories(
        entityManagerFactoryRef = "DemoEntityManagerFactory", (inject bean in the followed configuration class interact with database)
        transactionManagerRef = "DemoTransactionManager", (inject bean in the followed configuration class handle tracsation)
        basePackages = {"com.mwu.myv1.repository.mysql.primary" (implement for this)} 
    )
    class FirstDataBaseConfig{
            public static final String PERSISTENCE_UNIT_NAME = "Demo";(the unique name within the applciation)
            public static final String MODEL_PACKAGE = "com.mwu.myv1.model.mysql";(the entity should be used)

            @Bean(name = "DemoDataSource")                          (the bean name which is  in container)
            @Primary                                                (order)
            @ConfigurationProperties("spring.datasource.demo")      (yml file and inteac )
            (The DataSource class in the demoDataSource() method is used to configure and create a connection pool to the database. )
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

    public final class JpaAdditionalPropertiesHelper {
    public static Map<String, String> additionalProperties() {
        Map<String, String> properties = new HashMap<>();
        properties.put(
                "hibernate.physical_naming_strategy",
                "org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy");
        properties.put(
                "hibernate.implicit_naming_strategy",
                "org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy");
        return properties;
    }
    }


how to use env file variables to set the values in application.properties file
    in Edit Configuration -> Enable EnvFile -> import the env file
how to set spring.profiles.active (use which one to config, application.yml application-dev.yml, application-prod.yml) 
    spring.profiles.active=${ACTIVE_PROFILE:dev}


@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
     which specifies that the JSON property names should follow the snake_case naming convention when the object is serialized or deserialized.

except JPA(hibernate) also can use Spring – NamedParameterJdbcTemplate
The Java Database Connectivity (JDBC API) provides universal data access from any data sources(including relational databases, spreadsheets & flat files). The JdbcTemplate is the most basic and classic approach for data access. The NamedParameterJdbcTemplate wraps the JdbcTemplate and allows the use of named parameters instead of the traditional JDBC ‘?’ placeholder.
https://www.geeksforgeeks.org/spring-namedparameterjdbctemplate/


        
	
	

	

