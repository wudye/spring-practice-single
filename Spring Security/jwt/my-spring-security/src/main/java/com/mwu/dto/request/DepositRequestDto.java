package com.mwu.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DepositRequestDto {
    @NotBlank(message = "currency missing")
    @Pattern(regexp = "^(NGN|EUR|USD)$")
    private String currency;

    @NotBlank(message = "amount missing")
    @DecimalMin(value = "50", inclusive = true, message = "amount must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "amount must be a valid number")
    private BigDecimal amount;

    @NotBlank(message = "account number missing")
    private String walletAccountNumber;
    @NotBlank(message = "source account number missing")
    private String sourceAccountNumber;
    @NotBlank(message = "source bank missing")
    private String sourceAccountBankName;
    private String narration;



}
