package com.mwu.service;

import com.mwu.common.APIResponse;
import com.mwu.dto.response.TransactionHistoryResponseDto;
import com.mwu.model.Transaction;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountService {
    ResponseEntity<APIResponse<List<TransactionHistoryResponseDto>>> getTransactionHistory(Long transactionHistoryResponseDto);

    void saveTransactionRecord(Transaction transaction);
}
