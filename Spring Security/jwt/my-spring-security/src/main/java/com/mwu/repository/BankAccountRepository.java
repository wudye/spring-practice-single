package com.mwu.repository;
import com.mwu.model.BankAccount;
import com.mwu.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    BankAccount findByAccountNumberAndBankName(String accountNumber, String bankName);

}
