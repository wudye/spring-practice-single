package com.mwu.myv1.config.swaggerScheduler;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;



@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public-api")
                .pathsToMatch("/api/public/**")
                .packagesToScan("com.mwu.myv1.controller")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("My API DEMO");

        Contact contact = new Contact();
        contact.setName("My Name");
        contact.setEmail("my email");

        Info info = new Info()
                .title("My demo API")
                .version("1.0")
                .description("My demo API for all java tech stack")
                .contact(contact);
        return new OpenAPI().info(info).servers(List.of(server));
    }
}
