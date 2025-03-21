package com.mwu.repository;
import com.mwu.model.Transaction;
import com.mwu.model.WalletAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WalletRepository extends JpaRepository<WalletAccount, Long> {

    WalletAccount findByAccountNumber(String accountNumber);

    List<WalletAccount> findAllByCustomerId(Long accountId);
}
