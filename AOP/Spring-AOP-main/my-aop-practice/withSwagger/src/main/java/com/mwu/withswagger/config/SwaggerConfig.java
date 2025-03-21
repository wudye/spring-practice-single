package com.mwu.withswagger.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Local server");

        Contact contact = new Contact();
        contact.setName("Ming Wu");
        contact.setUrl("mingwei.wu@hotmail.com");

        Info info = new Info();
        info.setTitle("Invoice API");
        info.setVersion("1.0");
        info.setDescription("This is Invoice API");
        info.setContact(contact);

        return new OpenAPI()
                .info(info)
                .addServersItem(server);
    }
}