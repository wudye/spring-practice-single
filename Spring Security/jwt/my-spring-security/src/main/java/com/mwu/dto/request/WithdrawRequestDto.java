package com.mwu.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class WithdrawRequestDto {

    @NotNull(message = "missing amount to withdraw")
    private BigDecimal withdrawalAmount;
    @NotBlank(message = "account number missing")
    private String walletAccountNumber;
    @NotBlank(message = "currency missing")
    private String currency;
    private String narration;
}
