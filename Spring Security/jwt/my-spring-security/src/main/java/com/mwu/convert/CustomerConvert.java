package com.mwu.convert;

import com.mwu.dto.request.RegistrationRequestDto;
import com.mwu.model.Customer;
import com.mwu.model.CustomerType;

public class CustomerConvert {

    public static Customer convertDtoToCustomer(RegistrationRequestDto requestDto) {

        Customer customer = new Customer();
        customer.setAddress(requestDto.getAddress());
        customer.setEmail(requestDto.getEmail());
        customer.setCustomerType(requestDto.getCustomerType().equals("C") ? CustomerType.CORPORATE : CustomerType.INDIVIDUAL);
        customer.setFirstName(requestDto.getFirstName());
        customer.setLastName(requestDto.getLastName());
        customer.setPhoneNumber(requestDto.getPhoneNumber());
        customer.setUsername(requestDto.getUsername());
        return customer;
    }

    }
