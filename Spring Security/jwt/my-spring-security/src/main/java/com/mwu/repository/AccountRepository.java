package com.mwu.repository;
import com.mwu.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByWalletAccountId(Long walletId);
}
