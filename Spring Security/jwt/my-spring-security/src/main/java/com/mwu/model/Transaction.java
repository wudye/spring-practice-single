package com.mwu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaction_data")
@EntityListeners(value = AuditingEntityListener.class)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "wallet_account_id\", nullable = false)
//    private WalletAccount walletAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_account_id", nullable = false)
    private WalletAccount walletAccount;



    private String narration;

    private BigDecimal amount;


    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @CreatedDate
    private LocalDateTime transactionDate;

    public Transaction(BigDecimal amount, TransactionType transactionType, WalletAccount destinationAccount, String narration) {
        this.amount = amount;
        this.transactionType = transactionType;
        this.walletAccount = destinationAccount;
        this.narration = narration;
    }
}
