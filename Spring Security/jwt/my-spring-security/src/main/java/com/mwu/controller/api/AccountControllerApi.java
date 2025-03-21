package com.mwu.controller.api;

import com.mwu.common.APIResponse;
import com.mwu.dto.response.TransactionHistoryResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AccountControllerApi {
    ResponseEntity<APIResponse<List<TransactionHistoryResponseDto>>> getTransactionHistory(@RequestParam Long accountId);

}
