package com.mwu.model;

import com.mwu.utils.AccountUtil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account_data")
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
public class WalletAccount {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String accountNumber = AccountUtil.generateRandomStringAccount();


    @Enumerated(EnumType.STRING)
    private Currency currency;
    private BigDecimal accountBalance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @CreatedDate
    private LocalDateTime createdTime;

    @LastModifiedDate
    private LocalDateTime modifiedTime;

    @OneToMany(mappedBy = "walletAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();


    public WalletAccount(Currency currency, BigDecimal accountBalance, Customer customer) {
        this.currency = currency;
        this.accountBalance = accountBalance;
        this.customer = customer;
    }

    public WalletAccount() {
        this.accountNumber = AccountUtil.generateRandomStringAccount();
    }

    public void addTransaction(Transaction transaction) {
        List<Transaction> transactionList = getTransactions();
        if (transactionList.isEmpty()){
            transactions.add(transaction);
        }
        transactionList.add(transaction);
        transaction.setWalletAccount(this);
    }
}
