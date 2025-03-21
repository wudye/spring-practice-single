package com.mwu.jwt;

import com.mwu.exceptions.NotFoundException;
import com.mwu.model.Customer;
import com.mwu.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private  CustomerRepository userRepositor;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Customer customer =  userRepositor.findByUsername(username);
        if (customer != null) {
            return new CustomUserDetails(customer);
        }
        throw new NotFoundException("User not found with username: " + username);

    }
}
