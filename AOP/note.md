AOP
source code from https://github.com/akshu-dh/Spring-AOP/tree/main
article
    https://javatechonline.com/how-to-implement-aop-in-spring-boot-application/
1.add dependency
<dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-aop</artifactId>
</dependency>

2. add @EnableAspectJAutoProxy with @SpringBootApplication

3. Create one Aspect class as InvoiceAspect.java. Further, in this class define pointcuts & advices
    @Aspect
    @Component
    @Pointcut("execution(business service method)")
    pointcut methods in AspectJ are used solely to define pointcut expressions and should not contain executable code. Pointcut methods are intended to be empty and serve only as markers for the pointcut expressions.
    Every Advice Type Annotation must describe related pointcut information in the bracket.
    @Before
    @After 
    @AfterReturning
    @AfterThrowing
    @Around 
    Method annotated with @Around represents the Around Advice. It executes in two parts. Some part executes before the execution of business method, whereas other part executes after the execution of business method.
    Method annotated with @Around represents the Around Advice. It executes in two parts. Some part executes before the execution of business method, whereas other part executes after the execution of business method.


with swagger 
https://bell-sw.com/blog/documenting-rest-api-with-swagger-in-spring-boot-3/
https://springdoc.org/#Introduction
            <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>2.8.5</version>
        </dependency>

in application.properties 
    set the springdoc path
    springdoc.api-docs.path=/api-docs
if set this path can open http://localhost:8080/api-docs to check all interfaces

config swagger use 
    @Configuration to set name ,url ect..
open http://localhost:8080/swagger-ui/index.html to check 
annotations:
@Type
@Operation
@ApiResponses
@Parameter
@Schema
how to use these annotations accurate reading the tutorials


@NotNull, @Min, @Max, and @Size from Bean Validation combine it to do better controll
    

Log
    config it in application.properties
    especially give name and path set log level
        logging.file.name=logs/withSwagger-application.log
        logging.file.path=logs
        logging.level.root=INFO
        logging.level.com.withSwagger=INFO
logging plays a critical role in troubleshooting, debugging, and tracking the behavior of applications.
spring boot provide log
use the
 private static final Logger log = LoggerFactory.getLogger(classnametolog.class);
Lombok also provide @Slf4j and @Logging
https://medium.com/@AlexanderObregon/enhancing-logging-with-log-and-slf4j-in-spring-boot-applications-f7e70c6e4cc7


set global exception for  controller
use @RestControllerAdvice 
    sepcify 

    exception happens to return this status @ResponseStatus(code = HttpStatus.NOT_FOUND)
	this exception happend will invoke the global execption @ExceptionHandler({ com.mwu.withswagger.exception.InvalidInputException.class })
	public ErrorResponseDTO InvalidWalletBalanceException(Exception exception, HttpServletRequest request) {
		return new ErrorResponseDTO(new Date(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(),
				exception.getMessage(), request.getRequestURI());
	}

