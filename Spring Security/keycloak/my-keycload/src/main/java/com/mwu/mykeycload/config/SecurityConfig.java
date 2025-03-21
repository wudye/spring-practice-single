package com.mwu.mykeycload.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/").permitAll()
                                .requestMatchers("/menu").authenticated()
                                .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 ->
                        oauth2.loginPage("/oauth2/authorization/keycloak")
                                .defaultSuccessUrl("/menu", true)
                )
                .logout(longout ->
                        longout.logoutSuccessUrl("/")
                                .invalidateHttpSession(true)
                                .clearAuthentication(true)
                                .deleteCookies("JSESSIONID")
                );
        return http.build();
    }
}
