package com.bank.service;

import com.bank.domain.Account;
import com.bank.domain.Customer;
import com.bank.dto.AccountInfoDto;
import com.bank.repository.AccountRepository;
import com.bank.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    // 계좌 생성
    public Account createAccount(String customerSsn, String customerName, String accountType) {

        // 고객 조회
        Customer customer = customerRepository.findById(customerSsn)
                .orElseThrow(() -> new RuntimeException("고객을 찾을 수 없습니다."));

        // 이름 검증
        if (!customer.getCustomerName().equals(customerName)) {
            throw new RuntimeException("입력한 고객 이름이 실제 고객 정보와 일치하지 않습니다.");
        }

        // 계좌 생성
        Account newAccount = new Account();
        newAccount.setAccountType(accountType);
        newAccount.setBalance(BigDecimal.ZERO);
        newAccount.setCardRequest(false);
        newAccount.setOpenDate(LocalDate.now());
        newAccount.setCustomer(customer);

        return accountRepository.save(newAccount);
    }

    // 이름으로 계좌 조회 후 DTO 반환
    public List<AccountInfoDto> getAccountsByCustomerName(String customerName) {
        return accountRepository.findByCustomerName(customerName).stream()
                .map(a -> new AccountInfoDto(
                        a.getCustomer().getCustomerName(),
                        a.getCustomer().getCustomerSsn(),
                        a.getAccountId(),
                        a.getBalance()
                ))
                .collect(Collectors.toList());
    }

    // 이름으로 계좌 조회 후 개설일 기준 정렬 DTO 반환
    public List<AccountInfoDto> getAccountsByCustomerNameSorted(String customerName) {
        return accountRepository.findByCustomerNameOrderByOpenDateAsc(customerName).stream()
                .map(a -> new AccountInfoDto(
                        a.getCustomer().getCustomerName(),
                        a.getCustomer().getCustomerSsn(),
                        a.getAccountId(),
                        a.getBalance()
                ))
                .collect(Collectors.toList());
    }
}
