package com.mwu.controller;

import com.mwu.common.APIResponse;
import com.mwu.controller.api.AccountControllerApi;
import com.mwu.dto.response.TransactionHistoryResponseDto;
import com.mwu.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
@Tag(name = "Account", description = "Account API")
@RequiredArgsConstructor
public class AuthController implements AccountControllerApi {

    private final AccountService accountService;


    @Override
    @GetMapping(value = "/transaction-history", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Get Transaction History",
            description = "Get transaction history for a given account",
            parameters = {
                    @Parameter(name = "accountId", description = "Account Id", required = true)
            })
//    @Operation(
//            summary = "Get Transaction History",
//            description = "Fetch the transaction history for a specific account by its ID",
//            parameters = @Parameter(name = "accountId",
//                    description = "The ID of the account whose transaction history is to be retrieved",
//                    required = true,
//                    example = "12345"
//            )
//    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "00", description = "Operation Successful."),
            @ApiResponse(responseCode = "01", description = "Operation Failed.")})
            public ResponseEntity<APIResponse<List<TransactionHistoryResponseDto>>> getTransactionHistory(@RequestParam Long accountId) {
        return accountService.getTransactionHistory(accountId);
    }

}
