sprint security, jwt, swagger, log, aop
original repository from 
https://github.com/bigboytimi/multicurrency-wallet

spring security
    before config security need
    BCryptPasswordEncoder decode, encode password
    UserDetailsService for check the password and use in controller to authenticate
        The UserDetailsService interface has a single method, loadUserByUsername(String username), which is responsible for locating the user based on the provided username. The method returns a UserDetails object that contains the user's information, such as username, password, and granted authorities (roles and permissions).
    AuthenticationProvider
    AuthenticationManager
        When an authentication request is made, the AuthenticationManager attempts to authenticate the request by passing it to each configured AuthenticationProvider until one of them successfully authenticates the request or all of them fail.
        The AuthenticationProvider is an interface that performs the actual authentication logic. It is responsible for validating the credentials of a user and returning an Authentication object if the authentication is successful. Multiple AuthenticationProvider instances can be configured to handle different types of authentication (e.g., username/password, OAuth, etc.).
        The AuthenticationProvider is an interface that performs the actual authentication logic. It is responsible for validating the credentials of a user and returning an Authentication object if the authentication is successful. Multiple AuthenticationProvider instances can be configured to handle different types of authentication (e.g., username/password, OAuth, etc.).





in entity use @EntityListeners(AuditingEntityListener.class)
This listener is part of the Spring Data JPA auditing framework and is used to automatically populate auditing fields such as createdDate, lastModifiedDate, createdBy, and lastModifiedBy whenever the entity is persisted or updated.
it will automatically change these fileds
in main function add @EnableJpaAuditing

    @CreatedDate
    @LastModifiedDate
    @OneToOne @OneToMany(mapperby="class") 
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @ManyToMany(mappedBy = "transactions")
    @ManyToMany
    @JoinTable(
    name = "wallet_account_transaction",
    joinColumns = @JoinColumn(name = "wallet_account_id"),
    inverseJoinColumns = @JoinColumn(name = "transaction_id")


from validation
    @Notblanck
    @Pattern
    @DecimalMin
    @Digits


