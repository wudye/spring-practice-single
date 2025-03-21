package com.mwu.service.impl;

import com.mwu.common.APIResponse;
import com.mwu.common.ApiResponseCodes;
import com.mwu.dto.response.TransactionHistoryResponseDto;
import com.mwu.exceptions.ApiException;
import com.mwu.model.Transaction;
import com.mwu.repository.AccountRepository;
import com.mwu.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;



    @Override
    public ResponseEntity<APIResponse<List<TransactionHistoryResponseDto>>> getTransactionHistory(Long accountId) {
        List<Transaction> transactionList = accountRepository.findByWalletAccountId(accountId);

        if (transactionList.isEmpty()) {
            throw new ApiException(String.format("No transaction history found for wallet Id %s", accountId));
        }

        List<TransactionHistoryResponseDto> transactionHistoryResponseDtoList = mapToResponse(transactionList);
        return ResponseEntity.ok(setSuccessResponse(transactionHistoryResponseDtoList));
    }


    @Override
    public void saveTransactionRecord(Transaction transaction) {
        accountRepository.save(transaction);
    }

    public static List<TransactionHistoryResponseDto> mapToResponse(List<Transaction> transactionList) {
        return transactionList.stream()
                .map(transaction -> new TransactionHistoryResponseDto(
                        transaction.getTransactionType(),
                        transaction.getAmount(),
                        transaction.getTransactionDate(),
                        transaction.getNarration()  ) {
                })
                .collect(Collectors.toList());


    }


        private static APIResponse setSuccessResponse(Object responseData){
        return new APIResponse<>(ApiResponseCodes.SUCCESS.getCode(), ApiResponseCodes.SUCCESS.getStatus(), responseData);

    }
}