package com.mwu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

// In summary, this configuration class customizes the Spring MVC setup by adding
// a MappingJackson2HttpMessageConverter to the list of message converters.
// This enables the application to automatically convert Java objects to JSON
// and JSON to Java objects in HTTP communications, leveraging the powerful Jackson
// library for JSON processing. This is particularly useful for
// RESTful web services where JSON is a common data format.
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter());
    }

}
