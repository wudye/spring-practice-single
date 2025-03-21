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

for create table in Mongodb
@Document is a Spring Data Mongo annotation while @Entity is part of Java Persistence API (JPA).

