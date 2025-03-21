package com.mwu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer_data")
@EntityListeners(AuditingEntityListener.class)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String address;
    private String phoneNumber;
    @Column
    private String email;
    @Enumerated(EnumType.STRING)
    private CustomerType customerType;

    @CreatedDate
    private LocalDateTime createdTime;

    @LastModifiedDate
    private LocalDateTime modifiedTime;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WalletAccount> walletAccounts;

    @PrePersist
    public void initializeWallets() {
        this.walletAccounts = List.of(
                new WalletAccount(Currency.NGN, BigDecimal.ZERO, this),
                new WalletAccount(Currency.USD, BigDecimal.ZERO, this),
                new WalletAccount(Currency.EUR, BigDecimal.ZERO, this)
        );
    }
}
