package com.bank.service;

import com.bank.domain.Customer;
import com.bank.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer createCustomer(String ssn, String name){
        Customer c = new Customer();
        c.setCustomerSsn(ssn);     // PK
        c.setCustomerName(name);    // 이름
        // 나머지 컬럼은 필요하면 set 해주세요
        return customerRepository.save(c);
    }
}
