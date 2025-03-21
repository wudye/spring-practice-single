package com.mwu.dto.response;

import com.mwu.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionHistoryResponseDto {
    private TransactionType transactionType;
    private BigDecimal amount;
    private LocalDateTime createdTime;
    private String narration;
}
