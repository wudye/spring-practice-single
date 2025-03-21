package com.mwu.service.impl;

import com.mwu.common.APIResponse;
import com.mwu.common.ApiResponseCodes;
import com.mwu.convert.CustomerConvert;
import com.mwu.dto.WalletBalanceAndCurrency;
import com.mwu.dto.request.LoginRequestDto;
import com.mwu.dto.request.RegistrationRequestDto;
import com.mwu.dto.response.RegistrationResponseDto;
import com.mwu.exceptions.ApiException;
import com.mwu.exceptions.NotFoundException;
import com.mwu.exceptions.ValidationException;
import com.mwu.jwt.JwtUtil;
import com.mwu.model.Customer;
import com.mwu.repository.CustomerRepository;
import com.mwu.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {


    private final BCryptPasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(BCryptPasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil, CustomerRepository customerRepository) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.customerRepository = customerRepository;
    }

    @Override
    public ResponseEntity<APIResponse<String>> authenticateAndReturnToken(LoginRequestDto loginRequestDto) {
        Customer customer = customerRepository.findByUsername(loginRequestDto.getUsername());

        if (customer == null){
            throw new NotFoundException("User not found. Please contact support");
        }

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword()));
        } catch (Exception e) {
            throw new ApiException("Invalid username or password. Kindly try again with valid credentials.");
        }

        String encodedCredentials = jwtUtil.generateToken(new User(customer.getUsername(), customer.getPassword(), new ArrayList<>()));

        return ResponseEntity.ok(setSuccessResponse(encodedCredentials));
    }

    @Override
    public ResponseEntity<APIResponse<RegistrationResponseDto>> register(RegistrationRequestDto registrationRequestDto){

        boolean existsByEmail = customerRepository.existsByEmail(registrationRequestDto.getEmail());
        boolean existsByPhoneNumber = customerRepository.existsByPhoneNumber(registrationRequestDto.getPhoneNumber());

        if (existsByEmail || existsByPhoneNumber){
            throw new ValidationException("Mobile number or Email already exists. Please check and Try Again.");
        }


        String password = passwordEncoder.encode(registrationRequestDto.getPassword());

        Customer customer = CustomerConvert.convertDtoToCustomer(registrationRequestDto);
        customer.setPassword(password);

        try {
            customer = customerRepository.save(customer);
        } catch (DataIntegrityViolationException e) {
            throw new ValidationException("Database constraint violation, profile may exists with same values. Please try again or contact support.");
        }

        RegistrationResponseDto registrationResponseDto = new RegistrationResponseDto();
        registrationResponseDto.setAccountName(customer.getFirstName().concat(" ").concat(customer.getLastName()));
        registrationResponseDto.setWallet(getWalletDetailsFromCustomer(customer));

        return ResponseEntity.ok(setSuccessResponse(registrationResponseDto));
    }

    @Override
    public Customer getCustomerById(Long accountId) {
        return customerRepository.findById(accountId).orElseThrow(()->new ApiException("Customer Not Found"));
    }


    public String getAccountNumberFromCustomer(Customer customer) {
        if (customer.getWalletAccounts() != null && !customer.getWalletAccounts().isEmpty()) {
            return customer.getWalletAccounts().get(0).getAccountNumber();
        }
        throw new NotFoundException("Account Not Found");
    }

    private List<WalletBalanceAndCurrency> getWalletDetailsFromCustomer(Customer customer) {
        if (customer.getWalletAccounts() != null && !customer.getWalletAccounts().isEmpty()) {
            return customer.getWalletAccounts().stream()
                    .map(wallet -> new WalletBalanceAndCurrency(wallet.getAccountBalance(), wallet.getCurrency().name(), wallet.getAccountNumber()))
                    .collect(Collectors.toList());
        }
        return List.of();
    }

    private static APIResponse setSuccessResponse(Object responseData){
        return new APIResponse<>(ApiResponseCodes.SUCCESS.getCode(),ApiResponseCodes.SUCCESS.getStatus(), responseData);

    }
}
