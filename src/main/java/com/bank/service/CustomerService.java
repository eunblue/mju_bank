package com.bank.service;

import com.bank.domain.Customer;
import com.bank.dto.BirthdayDto;
import com.bank.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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

    public BirthdayDto getUpcomingBirthdayCustomerDto() {

        Customer customer = customerRepository.findUpcomingBirthday(PageRequest.of(0, 1))
                .stream()
                .findFirst()
                .orElse(null);

        if (customer == null) return null;

        LocalDate today = LocalDate.now();
        LocalDate birthdayThisYear = customer.getBirthDate().withYear(today.getYear());

        if (birthdayThisYear.isBefore(today)) {
            birthdayThisYear = birthdayThisYear.plusYears(1);
        }

        long dday = ChronoUnit.DAYS.between(today, birthdayThisYear);

        return new BirthdayDto(
                customer.getCustomerName(),
                customer.getAddress(),
                customer.getBirthDate(),
                dday
        );
    }
}
