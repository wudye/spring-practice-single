package com.mwu.service.impl;

import com.mwu.model.BankAccount;
import com.mwu.model.Currency;
import com.mwu.repository.BankAccountRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }



    @PostConstruct
    public void initializeBankAccounts() {
        if (bankAccountRepository.count() == 0) {
            bankAccountRepository.save(new BankAccount("1479801391", Currency.NGN, BigDecimal.valueOf(100000), "meCashNGN"));
            bankAccountRepository.save(new BankAccount("4569801376", Currency.USD, BigDecimal.valueOf(100000), "meCashUSD"));
            bankAccountRepository.save(new BankAccount("3802801324", Currency.EUR, BigDecimal.valueOf(100000), "meCashEUR"));
            bankAccountRepository.save(new BankAccount("1479801390", Currency.NGN, BigDecimal.valueOf(100000), "meCashNGN"));
            bankAccountRepository.save(new BankAccount("4569801312", Currency.USD, BigDecimal.valueOf(100000), "meCashUSD"));
            bankAccountRepository.save(new BankAccount("3809193356", Currency.EUR, BigDecimal.valueOf(100000), "meCashEUR"));

        }
    }

    public BankAccount getByBankAccountAndName(String sourceAccountNumber, String name) {
        return bankAccountRepository.findByAccountNumberAndBankName(sourceAccountNumber, name);
    }

    public void updateBalance(BankAccount sourceAccount) {
        bankAccountRepository.save(sourceAccount);
    }
}
