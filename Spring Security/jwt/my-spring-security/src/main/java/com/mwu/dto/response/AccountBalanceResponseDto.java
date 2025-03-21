package com.mwu.dto.response;

import com.mwu.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountBalanceResponseDto {
    private BigDecimal accountBalance;
    private String accountNumber;
    private Currency currency;
}
