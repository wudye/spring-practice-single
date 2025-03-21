
package com.mwu.repository;
import com.mwu.model.Customer;
import com.mwu.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUsername(String username);

    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
}
