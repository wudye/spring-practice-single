package com.mwu.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequestDto {
    @NotBlank(message = "currency is missing")
    private String currency;
    @NotBlank(message = "bankName is missing")
    private String bankName;
    @NotBlank(message = "sourceAccountNumber is missing")
    private String sourceAccountNumber;
    @NotBlank(message = "destinationAccountNumber is missing")
    private String destinationAccountNumber;
    @NotBlank(message = "destinationAccountName is missing")
    private String destinationAccountName;
    @NotNull(message = "Please set amount")
    private BigDecimal amount;

    private String narration;
}
