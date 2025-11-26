package com.bank.service;

import com.bank.domain.Account;
import com.bank.domain.Card;
import com.bank.domain.Customer;
import com.bank.domain.Transaction;
import com.bank.repository.AccountRepository;
import com.bank.repository.CardRepository;
import com.bank.repository.CustomerRepository;
import com.bank.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final CardRepository cardRepository;
    private final TransactionRepository transactionRepository;

    // 1. 고객 계좌 + 잔액 조회
    public List<Account> getAccountsByName(String name) {
        return accountRepository.findByCustomer_CustomerName(name);
    }

    // 2. 계좌 개설 순 정렬
    public List<Account> getAccountsByNameSorted(String name) {
        return accountRepository.findByCustomer_CustomerNameOrderByOpenDate(name);
    }

    // 3. 최근 한달 거래내역
    public List<Transaction> getTransactionsLastMonth(Long accountId) {
        LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);
        return transactionRepository.findLastMonth(accountId, oneMonthAgo);
    }

    // 4. 고객의 카드 정보 조회
    public List<Card> getCardsByName(String name) {
        return cardRepository.findByCustomer_CustomerName(name);
    }

    // 5. 생일이 가장 가까운 고객
    public Customer getClosestBirthday() {
        return customerRepository.findUpcomingBirthday().get(0);
    }
    
    //6. 계좌 생성
    public Account createAccount(Integer customerId, String accountType, Boolean cardRequest){
        Customer customer =  customerRepository.findById(String.valueOf(customerId))
                .orElseThrow(() -> new IllegalArgumentException("고객을 찾을 수 없습니다. ID = " + customerId));

        Account account = new Account();
        account.setAccountType(accountType);
        account.setBalance(BigDecimal.ZERO);  // 신규 계좌는 기본 잔액 0
        account.setCardRequest(cardRequest);
        account.setOpenDate(LocalDate.now());
        account.setCustomer(customer);

        return accountRepository.save(account);
    }

}

