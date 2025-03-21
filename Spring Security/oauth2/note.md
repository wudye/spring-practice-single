original: https://github.com/danvega/spring-boot-oauth-demo/tree/main

in securityfilterchain config
formLogin(form-> form 
    .loginPage("/yourlogin")
    .defaultSuccessUrl("/redirect", true)
    .permitAll()) (after login success)
.oauth2Login(oauth2 ->oauth2
    .loginPage("/yourlogin")
    .defaultSuccessUrl("/redirect", true))
.logout(logout ->logout 
    .logoutSuccessUrl("/yourlogout")
    .permitAll)


in controller use api according to frontend tech pass the user login info to frontend
provide csrf options

work with JWT

                .authenticationProvider(authenticationProvider())
                (
                            DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
                            authProvider.setUserDetailsService(userDetailsService());
                            (
                                need a class to implements UserDetailsService
                                to override the method loadUserByUsername
                            )
                            authProvider.setPasswordEncoder(passwordEncoder());
                            (
                                provide   new BCryptPasswordEncoder();

                            )
                )
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

for auth login  in controller
after login 
    need 
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword()));
    to check if is qualified or not 
    then create jwt token by jwtUtil
         String encodedCredentials = jwtUtil.generateToken(new User(customer.getUsername(), customer.getPassword(), new ArrayList<>()));

so config  need @Bean 
    for securityfilterchain authenticationProvider(authenticationProvider())
        UserDetailsService
            need a class implements UserDetails to do loadUserByUsername
        BCryptPasswordEncoder
        AuthenticationProvider
    for controller login check 
       AuthenticationManager
    need JwtFileter(@Autowired) extends OncePerRequestFilter
        need UserDetailsService to get use info from input name
        config UsernamePasswordAuthenticationToken and SecurityContextHolder
        need jwtUtil to extract info and check validation from input token

