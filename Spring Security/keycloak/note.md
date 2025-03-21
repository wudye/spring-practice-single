https://dev.to/bansikah/keycloak-and-spring-boot-the-ultimate-guide-to-implementing-single-sign-on-1af7
https://medium.com/javarevisited/keycloak-integration-with-spring-security-6-37999f43ec85
create realm
in new created realm  create client
    set clientId same as realm name
    get realm secret key
    set root url(app root path)
    set valid redirect url(after auth can go to path)
in new created realm  create user
    set password

confgi properties.yml
spring:
  security:
    oauth2:
      client:
        registration:
          keycloak:
            client-id: food-ordering-client
            client-secret: N5CwGQsY1inE8RcoHJZaqdpzaQ02Xyi1
            scope:
              - openid
              - profile
              - email
            redirect-uri: http://10.18.189.188:8082/login/oauth2/code/keycloak (keycloak login page)
        provider:
            keycloak:
                issuer-uri: http://192.168.150.108:8080/realms/food-ordering-realm (realm created)


in SecurityFilterChain config
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