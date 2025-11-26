package com.bank.controller;

import com.bank.domain.Customer;
import com.bank.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository customerRepository;

    // 주민번호(PK) 기준 조회
    @GetMapping("/{ssn}")
    public Customer getCustomer(@PathVariable String ssn){
        return customerRepository.findById(ssn).orElse(null);
    }

    //고객 이름 조회
    @GetMapping("/find")
    public List<Customer> findCustomers(@RequestParam String name) {
        return customerRepository.findByCustomerName(name);
    }
}
