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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    // 계좌 생성
    public Account createAccount(String customerSsn, String customerName, String accountType) {
        Customer customer = customerRepository.findById(customerSsn)
                .orElseThrow(() -> new RuntimeException("고객을 찾을 수 없습니다."));

        if (!customer.getCustomerName().equals(customerName)) {
            throw new RuntimeException("입력한 고객 이름이 실제 고객 정보와 일치하지 않습니다.");
        }

        Account newAccount = new Account();
        newAccount.setAccountType(accountType);
        newAccount.setBalance(BigDecimal.ZERO);
        newAccount.setCardRequest(false);
        newAccount.setOpenDate(LocalDate.now());
        newAccount.setCustomer(customer);

        return accountRepository.save(newAccount);
    }

    // 이름으로 계좌 조회 후 고객별 그룹핑
    public Map<String, List<AccountInfoDto>> getAccountsGroupedByCustomer(String customerName) {
        return accountRepository.findByCustomerNameOrderByOpenDateAsc(customerName).stream()
                .collect(Collectors.groupingBy(
                        a -> a.getCustomer().getCustomerSsn(), // SSN 기준 그룹핑
                        LinkedHashMap::new, // 순서 유지
                        Collectors.mapping(
                                a -> new AccountInfoDto(
                                        a.getCustomer().getCustomerName(),
                                        a.getCustomer().getCustomerSsn(),
                                        a.getAccountId(),
                                        a.getBalance(),
                                        a.getAccountType()
                                ),
                                Collectors.toList()
                        )
                ));
    }
}
