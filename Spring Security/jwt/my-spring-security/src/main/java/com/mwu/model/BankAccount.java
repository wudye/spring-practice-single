package com.mwu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bank_account_data")
@EntityListeners(AuditingEntityListener.class)
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number", unique = true)
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(length = 3)
    private Currency currency;

    @Column(name = "account_balance")
    private BigDecimal accountBalance;

    @Column(name = "bank_name")
    private String bankName;

    public BankAccount(String number, Currency currency, BigDecimal bigDecimal, String meCashNGN) {
        this.accountNumber = number;
        this.currency = currency;
        this.accountBalance = bigDecimal;
        this.bankName = meCashNGN;

    }
}
