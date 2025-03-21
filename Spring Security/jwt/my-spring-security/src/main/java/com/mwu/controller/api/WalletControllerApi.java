package com.mwu.controller.api;

import com.mwu.common.APIResponse;
import com.mwu.dto.request.DepositRequestDto;
import com.mwu.dto.request.TransferRequestDto;
import com.mwu.dto.request.WithdrawRequestDto;
import com.mwu.dto.response.AccountBalanceResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface WalletControllerApi {
    ResponseEntity<APIResponse<String>> deposit(@RequestBody @Valid DepositRequestDto depositRequestDto);

    ResponseEntity<APIResponse<String>> transfer(@RequestBody @Valid TransferRequestDto transferRequestDto);

    ResponseEntity<APIResponse<String>> withdraw(@RequestBody @Valid WithdrawRequestDto withdrawRequestDto);

    ResponseEntity<APIResponse<List<AccountBalanceResponseDto>>> getAccountBalance(@RequestParam Long accountId);
}
