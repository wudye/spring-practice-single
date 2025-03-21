package com.mwu.service.impl;

import com.mwu.common.APIResponse;
import com.mwu.common.ApiResponseCodes;
import com.mwu.controller.UserController;
import com.mwu.dto.request.DepositRequestDto;
import com.mwu.dto.request.TransferRequestDto;
import com.mwu.dto.request.WithdrawRequestDto;
import com.mwu.dto.response.AccountBalanceResponseDto;
import com.mwu.exceptions.ApiException;
import com.mwu.model.*;
import com.mwu.repository.WalletRepository;
import com.mwu.service.CustomerService;
import com.mwu.service.WalletService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WalletServiceImpl implements WalletService {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final WalletRepository walletRepository;

    private final BankAccountService bankAccountService;
    private final CustomerService customerService;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository, BankAccountService bankAccountService, CustomerService customerService) {
        this.walletRepository = walletRepository;
        this.bankAccountService = bankAccountService;
        this.customerService = customerService;
    }


    @Override
    @Transactional
    public ResponseEntity<APIResponse<String>> processDeposit(DepositRequestDto depositRequestDto) {
        WalletAccount destinationAccount = walletRepository.findByAccountNumber(depositRequestDto.getWalletAccountNumber());

        if (destinationAccount == null || !destinationAccount.getCurrency().name().equals(depositRequestDto.getCurrency())){
            throw new ApiException(String.format("No wallet found for account number: %s with currency: %s",
                    depositRequestDto.getSourceAccountNumber(), depositRequestDto.getCurrency()));
        }

        BankAccount sourceAccount = bankAccountService.getByBankAccountAndName(depositRequestDto.getSourceAccountNumber(), depositRequestDto.getSourceAccountBankName());

        log.info("transfer processing from source {}, to destination {}", sourceAccount, destinationAccount);
        if (sourceAccount == null){
            throw new ApiException("Invalid source account. Kindly provide valid account number and account name");
        }

        if (!sourceAccount.getCurrency().equals(destinationAccount.getCurrency())){
            throw new ApiException("Currency Mismatch. Kindly provide valid currency");
        }

        if (!canDeposit(sourceAccount.getAccountBalance(), depositRequestDto.getAmount())){
            throw new ApiException("Insufficient balance");
        }

        sourceAccount.setAccountBalance(sourceAccount.getAccountBalance().subtract(depositRequestDto.getAmount()));
        destinationAccount.setAccountBalance(destinationAccount.getAccountBalance().add(depositRequestDto.getAmount()));

        try {
            bankAccountService.updateBalance(sourceAccount);

            Transaction transaction = new Transaction(depositRequestDto.getAmount(), TransactionType.DEPOSIT, destinationAccount, depositRequestDto.getNarration());

            destinationAccount.addTransaction(transaction);

            walletRepository.save(destinationAccount);
        }catch (Exception e){
            throw new ApiException(e.getMessage());
        }

        String responseMsg = String.format("Successful Credit Deposit From %s\n Amount: %s\n New Account Balance %s",
                sourceAccount.getAccountNumber(), depositRequestDto.getAmount(), destinationAccount.getAccountBalance());
        return ResponseEntity.ok(setSuccessResponse(responseMsg));
    }

    @Override
    @Transactional
    public ResponseEntity<APIResponse<String>> processTransfer(TransferRequestDto transferRequestDto) {
        WalletAccount sourceAccount = walletRepository.findByAccountNumber(transferRequestDto.getSourceAccountNumber());

        if (sourceAccount == null || !sourceAccount.getCurrency().name().equals(transferRequestDto.getCurrency())){
            throw new ApiException(String.format("No wallet found for account number: %s with currency: %s",
                    transferRequestDto.getSourceAccountNumber(), transferRequestDto.getCurrency()));
        }

        BankAccount destinationAccount = bankAccountService.getByBankAccountAndName(transferRequestDto.getDestinationAccountNumber(), transferRequestDto.getDestinationAccountName());

        if (destinationAccount == null){
            throw new ApiException("Invalid destination account. Kindly provide valid destination account & currency");
        }

        if (!sourceAccount.getCurrency().equals(destinationAccount.getCurrency())){
            throw new ApiException("Currency Mismatch. Kindly provide valid currency");
        }

        if (!canTransfer(sourceAccount.getAccountBalance(), transferRequestDto.getAmount())){
            throw new ApiException("Insufficient funds in source account. Kindly fund wallet");
        }

        destinationAccount.setAccountBalance(destinationAccount.getAccountBalance().add(transferRequestDto.getAmount()));
        sourceAccount.setAccountBalance(sourceAccount.getAccountBalance().subtract(transferRequestDto.getAmount()));


        try{
            bankAccountService.updateBalance(destinationAccount);

            Transaction transaction = new Transaction(transferRequestDto.getAmount(), TransactionType.TRANSFER, sourceAccount, transferRequestDto.getNarration());

            sourceAccount.addTransaction(transaction);

            walletRepository.save(sourceAccount);
        }catch (Exception e){
            throw new ApiException(e.getMessage());
        }

        String responseMsg = String.format("Successful Debit From Wallet Account %s\n Amount: %s\n New Account Balance %s",
                sourceAccount.getAccountNumber(), transferRequestDto.getAmount(), sourceAccount.getAccountBalance());
        return ResponseEntity.ok(setSuccessResponse(responseMsg));
    }

    @Override
    public ResponseEntity<APIResponse<String>> processWithdraw(WithdrawRequestDto withdrawRequestDto) {
        if (withdrawRequestDto.getWithdrawalAmount() == null || withdrawRequestDto.getWithdrawalAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ApiException("The withdrawal amount must be greater than zero.");
        }

        WalletAccount walletAccount = walletRepository.findByAccountNumber(withdrawRequestDto.getWalletAccountNumber());

        if (walletAccount == null){
            throw new ApiException(String.format("No wallet found for account number: %s with currency: %s",
                    withdrawRequestDto.getWalletAccountNumber(), withdrawRequestDto.getCurrency()));
        }

        if (walletAccount.getAccountBalance().compareTo(withdrawRequestDto.getWithdrawalAmount())< 0){
            throw new ApiException(String.format(
                    "Insufficient funds in wallet. Current balance: %s %s, Requested: %s %s",
                    walletAccount.getAccountBalance(), walletAccount.getAccountBalance(), withdrawRequestDto.getWithdrawalAmount(), withdrawRequestDto.getCurrency()));
        }

        BigDecimal newBalance = walletAccount.getAccountBalance().subtract(withdrawRequestDto.getWithdrawalAmount());
        walletAccount.setAccountBalance(newBalance);

        try {
            Transaction transaction = new Transaction(withdrawRequestDto.getWithdrawalAmount(), TransactionType.WITHDRAWAL, walletAccount, null);

            walletAccount.addTransaction(transaction);

            walletRepository.save(walletAccount);
        }catch (Exception e){
            throw new ApiException(e.getMessage());
        }

        String responseMsg = String.format("Successful Withdrawal From Wallet Account %s\n Amount: %s\n New Account Balance %s",
                walletAccount.getAccountNumber(), withdrawRequestDto.getWithdrawalAmount(), walletAccount.getAccountBalance());
        return ResponseEntity.ok(setSuccessResponse(responseMsg));
    }

    @Override
    public ResponseEntity<APIResponse<List<AccountBalanceResponseDto>>> getAccountBalance(Long accountId) {
        Customer customer = customerService.getCustomerById(accountId);

        List<WalletAccount> walletAccounts = walletRepository.findAllByCustomerId(customer.getId());

        if (walletAccounts.isEmpty()){
            throw new ApiException(String.format("Wallet account not found for customer with Id: %s",accountId));
        }

        List<AccountBalanceResponseDto> balanceResponseList = mapToResponse(walletAccounts);
        return ResponseEntity.ok(setSuccessResponse(balanceResponseList));
    }


    public boolean canDeposit(BigDecimal sourceAccountBalance, BigDecimal depositAmount) {
        return sourceAccountBalance.compareTo(depositAmount) > 0;
    }

    public boolean canTransfer(BigDecimal sourceAccountBalance, BigDecimal depositAmount) {
        return sourceAccountBalance.compareTo(depositAmount) > 0;
    }

    private static APIResponse setSuccessResponse(Object responseData){
        return new APIResponse<>(ApiResponseCodes.SUCCESS.getCode(),ApiResponseCodes.SUCCESS.getStatus(), responseData);
    }

    public static List<AccountBalanceResponseDto> mapToResponse(List<WalletAccount> walletAccounts) {
        return walletAccounts.stream()
                .map(wallet -> new AccountBalanceResponseDto(
                        wallet.getAccountBalance(),
                        wallet.getAccountNumber(),
                        wallet.getCurrency()
                ) {
                })
                .collect(Collectors.toList());
    }
}
