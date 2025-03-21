For Inverview
Security Oauth2, keycloak
redis, mutilple databases

My stragegy(experience, simple project)
Answer all questions should base on the experience in simple project not on the theroies or official tutotrials
If they insist, the interview should stop.
reason:
    the goal is to find a job to finish the task not study the theries and principles
    In this level as a beginner should focus on how to do not why and what
    with the job experiences to undeerstand why and what so can go to senior develper level
    (the top priority for a beginner is to do not to talk
    with the job should think why and what and how do better
    to many tech stacks if never use or only use it in simple project  just treat them as tools
    Anyway as a beginner should provide the basic experience to prove I can use tools in simple project
    )

When get a question about principle like 
IOC, AOP

should base on my experience using the spring boot one sentence give the answer 

1. AOP
what is AOP:
    AOP is aspect oriented programming
why and when use it  
    when I want to add some functianlities without releations with the businnes logical like add log 
how to use it
    config a class with  @Componet
    two ways 
        use @Pointcut("execution(business service method)") to specify a method
        without @Pointcut, just use like @Before or other Annotation to a class
        @Before("execution(* com.mwu.withswagger.service.EmployeeService.*(..))")

2. LOG
what is log
    get the information of the application
why and when use it
    want to track especially error infos
how to use it
    config it in application.properties
    especially give name and path set log level

SWAGGER
what is swagger
    provides tools to test, document .... for API
why and when use it
    want to test (postman) and document the api
how to use it
    config in openAPI customOpenAPI method, set the message 
    then can open the webpage to test and get document

3. Global exeception handle
what is global exception handler
    it is an exception to provide centrealized mechnisam to handle.
why and when use it
    it provide another way to handle exception, more info 
how to use it
    config a global execetion handle class with annotation @RestControllerAdvice
    @ResponseStatus(code = HttpStatus.NOT_FOUND) to set if it happens reponse status
    @ExceptionHandler({ com.mwu.withswagger.exception.sefldefinedExceptionhandle.class }) to set which 
    exception happens will invoke the global exception handler
    define it , if the invoking exception happens will use the global exception handle to replace it and give 
    repsonse status
    	public ErrorResponseDTO InvalidWalletBalanceException(Exception exception, HttpServletRequest request) {
		return new ErrorResponseDTO(new Date(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(),
				exception.getMessage(), request.getRequestURI());
	}

4. spring Security jwt
what is spring security jwt is a way to secure RESTful APIS using token.
    JWT includes claims ecoded by json object having authentication user info.
why and when to use it
    want to implement this security method.
how to use it 
    when user logged in ,it will create a jwt token to send it to frontend. The server validates the token to ensure the request is authenticated and authorized by the security config.
    login service
        1.get request, use       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword()));
        the authenticationManager.authenticate  to  AuthenticationProvider by it UserDetailsService get the info form database to do authenticate
        1. use jwtUtils to generateToken
    SecurityFilterChain config
        authenticationProvider, addFilterBefore, formLogin... to config
        need inject beans
            UserDetailsService(custom)
                need a class implements UserDetails to do loadUserByUsername
            BCryptPasswordEncoder
            AuthenticationProvider
                need UserDetailsService and passwordEncoder
            AuthenticationManager
        need @Component JwtFilter  extends OncePerRequestFilter to use doFilterInternal (for user logged in successfully if visit other pages will use this to check authentication)
            neew jwtUtils to generate and extrack token info
        need a class  implements UserDetails to get authenticated user info

5. spring Security Oauth2
what is spring Oauth2
    it can help use other company services like Google to do authenticate
why and when to use it
    If want to use loggin with other companies account wihout register
how to use it
    in properties.yml file to provide api keys
    in securiyfilterconfig to add oauth2Login option
the forntend also give the login page

6. spring Security keycloak
what is keycloak
    it is to do implement(single sign-on) to do authenticate and authorizate
how to use keycloak
    download the client and config in security and properties file
 

7. RESTful API
what is RESTfull API
    it is a way to follow rest architecture for designing network application.
why and when to use it
    when you want your application to be used by other applications or servcies on web.
    It use http request to perfomr crud on database
how to use it  
    create modal entity
    create repository to do crud for database
    create controller by service to use repository

8. Spring IOC
what is Sprint IOC
    to handle dependencies and lifecycle of beans
why and when use it
    focus on business logic
how to use it  
    use @Service, @Component, @Bean ... to create beans
    use method to inject like @Authowired  to use it  

9.  Spring MVC
what is spring MVC
    it is a framwork to build web application. model-view-controll
why and when to use 
    to build a web application
how to use 
    model use @Entity
    controll use @Controller 
    view for frontend html file 


10. Redis(cache and no cache)
what is redis

why and when to use it 

11. Mysql(mutilple, transaction, page)
how to handle one project uses mutilple databases (Mysql)
    in application file name seperately
    config in seperately by @Configuration


12. ActiveMQ
what is activeMQ
    activeMQ is used to send messages between two ro more applications
why and when to use it
    with activeMQ you can set up the flow of message based on what you need
    RabbitMQ focus on where messages should go
    activeMQ is for broker-centric architecture and supprot for  JMS
    Kafka is for distributed log architecure designed for hign-thoroughput and 
    real-time data streaming
how to use activeMQ
    deploy activeMQ and config in properties file and configuration class, create 
    producer and consumer classes and sent message in controller


13. RabbitMQ
what is RabbitMQ
    it is also a broker letting applications sent message like ActiveMQ but is good for distributed architecure
how to use it
    install rabbitmq, config in application file
    create a Queue, production, consumer, RabbitTemplate, if possage bind and use topic fanout


14. Kafka

15. JMS
what is jms
    jms( Java Messaging Service ) is an API sending messages between applications.
how to use it
    just use activeMQ

16.  broker-centric architecture
what is broker-centric architecture
    it is a central component to enable among services or applications to communicate sending messages

17. distributed  architecure
what is distributed  architecure
    Microservices in Spring Boot is an example of it.

18. @Scheduled
what is Scheduled annotation
    Scheduled lets to invoke a method in fixed time as you want eg. every minute
why and when to use this annotation
    when want to invoke a method a fixed time like every minute
how to use it
    add @EnableScheduling in main funciton
    add @Scheduled in the method
    combine @Aync to avoid block
    config threadpool for Executor
    use configed Executor to config SchedulingConfigurer

19. thread
Tomcat will handle thread do not need additionla local for java thread 

20. gmail sender
how to send email in spring boot gmail example
    get the gmail server password
        open google account -> security -> 2-Step Verification -> App passwords -> create App ->get password
    config application.properties 
    config @Service
        JavaMailSender for simple text email
        MimeMessage and MimeMessageHelper  for attachment email

21. elasticSearch


Spring AI
1. gemini
   https://github.com/danvega/hello-gemini
2. deepseek
https://github.com/danvega/deepseek-spring/tree/master
https://github.com/danvega/deepseek-java



Microserver
1. OpenFeign
what is openfeign
    services can use other service http request
why and when to use it
    want to use other services bussiness process
how to use it
    in every service main function add @EnableFeignClients
    the service want to use create a interface with @Service
    and @FeignClient(name = "need-service", url = "need service address")//add your appropriate port number
    then in class use the need servcie http request


