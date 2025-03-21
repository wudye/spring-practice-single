package com.mwu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletBalanceAndCurrency {
    private BigDecimal balance;
    private String currency;

    private String accountNumber;
}
