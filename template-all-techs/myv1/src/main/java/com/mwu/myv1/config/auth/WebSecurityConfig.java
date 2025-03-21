package com.mwu.myv1.config.auth;

import com.mwu.myv1.config.auth.filter.VerifyFixedTokenFilter;
import com.mwu.myv1.config.auth.filter.VerifyJwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    @Value("${spring.main.web-application-type}")
    private String applicationType;


    private static final String WEB_APPLICATION_TYPE = "servlet";

    private final AuthEntryPointJwt authEntryPointJwt;

    private final CustomAccessDeniedHandler accessDeniedHandler;

    private static final String[] AUTH_WHITELIST = {
            "/",
            "/api/login",
            // -- API document
            "/swagger-ui/**",
            "/v3/api-docs/**",
            // -- Prometheus
            "/actuator/**"
    };
    @Bean
    public VerifyJwtTokenFilter jwtAuthenticationFilter() {
        return new VerifyJwtTokenFilter();
    }

    @Bean
    public VerifyFixedTokenFilter fixedTokenAuthenticationFilter() {
        return new VerifyFixedTokenFilter();
    }
    @Bean
    public SecurityFilterChain customSecurityFilterChain(HttpSecurity http) throws Exception {
        if (applicationType.equals(WEB_APPLICATION_TYPE)) {
            http.cors(AbstractHttpConfigurer::disable).csrf(AbstractHttpConfigurer::disable);
            http.authorizeHttpRequests(auth ->
                    auth.requestMatchers(AUTH_WHITELIST).permitAll()
                            .requestMatchers("/api/admin/**").hasRole("ADMIN")
                            .requestMatchers("/api/db/**").hasAnyRole("ADMIN", "DBA")
                            .anyRequest().authenticated()
            );
            http.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
            http.exceptionHandling(e -> e.authenticationEntryPoint(authEntryPointJwt).accessDeniedHandler(accessDeniedHandler));
            http.addFilterAfter(fixedTokenAuthenticationFilter(), LogoutFilter.class);
            http.addFilterAfter(jwtAuthenticationFilter(), LogoutFilter.class);
        }
        return http.build();
    }

//     @Bean
//     public  SecurityFilterChain springSecurityFilterChain(HttpSecurity http) throws Exception {
//        if (applicationType.equals(WEB_APPLICATION_TYPE)) {
//            http.cors(AbstractHttpConfigurer::disable).csrf(AbstractHttpConfigurer::disable);
//            http.authorizeHttpRequests(auth ->
//                    auth.requestMatchers(AUTH_WHITELIST).permitAll()
//                            .requestMatchers("/api/admin/**").hasRole("ADMIN")
//                            .requestMatchers("/api/db/**").hasAnyRole("ADMIN","DBA")
//                            .anyRequest().authenticated()
//            );
//            http.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//            http.exceptionHandling(e -> e.authenticationEntryPoint(authEntryPointJwt).accessDeniedHandler(accessDeniedHandler));
//            http.addFilterAfter(fixedTokenAuthenticationFilter(), LogoutFilter.class);
//            http.addFilterAfter(jwtAuthenticationFilter(), LogoutFilter.class);
//        }
//        return http.build();
//     }

     @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setAllowedHeaders(
                Arrays.asList(
                        "Authorization",
                        "Content-Type",
                        "Cache-Control",
                        "Verify-Token",
                        "apikey"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
