package com.mwu.service;

import com.mwu.common.APIResponse;
import com.mwu.dto.request.DepositRequestDto;
import com.mwu.dto.request.TransferRequestDto;
import com.mwu.dto.request.WithdrawRequestDto;
import com.mwu.dto.response.AccountBalanceResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WalletService {
    ResponseEntity<APIResponse<String>> processDeposit(DepositRequestDto depositRequestDto);

    ResponseEntity<APIResponse<String>> processTransfer(TransferRequestDto transferRequestDto);

    ResponseEntity<APIResponse<String>> processWithdraw(WithdrawRequestDto withdrawRequestDto);

    ResponseEntity<APIResponse<List<AccountBalanceResponseDto>>> getAccountBalance(Long accountId);
}

