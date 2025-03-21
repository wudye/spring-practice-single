package com.mwu.controller;

import com.mwu.common.APIResponse;
import com.mwu.controller.api.WalletControllerApi;
import com.mwu.dto.request.DepositRequestDto;
import com.mwu.dto.request.TransferRequestDto;
import com.mwu.dto.request.WithdrawRequestDto;
import com.mwu.dto.response.AccountBalanceResponseDto;
import com.mwu.service.WalletService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/wallet")
@RestController
@Tag(name = "Wallet Management", description = "APIs for wallet operations like deposit, transfer, withdrawal, and balance inquiry")
public class WalletController implements WalletControllerApi {

    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @Override
    @PostMapping(value = "/deposit",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Deposit Funds",
            description = "Allows a user to deposit funds into their wallet.",
            requestBody = @RequestBody(
                    description = "Details of the deposit transaction",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DepositRequestDto.class)
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "00", description = "Operation Successful.", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = APIResponse.class)
            )),
            @ApiResponse(responseCode = "01", description = "Operation Failed."),
            @ApiResponse(responseCode = "02", description = "Operation Pending.")
    })
    public ResponseEntity<APIResponse<String>> deposit(@RequestBody @Valid DepositRequestDto depositRequestDto) {
        return walletService.processDeposit(depositRequestDto);
    }

    @Override
    @PostMapping(value = "/transfer",  produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Transfer Funds",
            description = "Allows a user to transfer funds from their wallet to another account.",
            requestBody = @RequestBody(
                    description = "Details of the transfer transaction",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TransferRequestDto.class)
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "00", description = "Operation Successful.", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = APIResponse.class)
            )),
            @ApiResponse(responseCode = "01", description = "Operation Failed."),
            @ApiResponse(responseCode = "02", description = "Operation Pending.")
    })
    public ResponseEntity<APIResponse<String>> transfer(@RequestBody @Valid TransferRequestDto transferRequestDto) {
        return walletService.processTransfer(transferRequestDto);
    }

    @Override
    @PostMapping(value = "/withdraw",  produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Withdraw Funds",
            description = "Allows a user to withdraw funds from their wallet.",
            requestBody = @RequestBody(
                    description = "Details of the withdrawal transaction",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = WithdrawRequestDto.class)
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "00", description = "Operation Successful.", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = APIResponse.class)
            )),
            @ApiResponse(responseCode = "01", description = "Operation Failed."),
            @ApiResponse(responseCode = "02", description = "Operation Pending.")
    })
    public ResponseEntity<APIResponse<String>> withdraw(@RequestBody @Valid WithdrawRequestDto withdrawRequestDto) {
        return walletService.processWithdraw(withdrawRequestDto);
    }

    @Override
    @GetMapping(value = "/balance", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get Account Balance",
            description = "Fetch the balance details of a specific wallet account.",
            parameters = @io.swagger.v3.oas.annotations.Parameter(
                    name = "accountId",
                    description = "The ID of the account whose balance is being requested",
                    required = true
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "00", description = "Operation Successful.", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = APIResponse.class)
            )),
            @ApiResponse(responseCode = "01", description = "Operation Failed."),
            @ApiResponse(responseCode = "02", description = "Operation Pending.")
    })
    public ResponseEntity<APIResponse<List<AccountBalanceResponseDto>>> getAccountBalance(@RequestParam Long customerId) {
        return walletService.getAccountBalance(customerId);
    }
}
