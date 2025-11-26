package com.bank.service;

import com.bank.domain.Account;
import com.bank.domain.Card;
import com.bank.domain.Customer;
import com.bank.repository.AccountRepository;
import com.bank.repository.CardRepository;
import com.bank.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final CardRepository cardRepository;

    public synchronized Card issueCard(String customerSsn, String customerName, Integer accountId, String cardType) {

        // 1️⃣ 고객 확인 (없으면 생성)
        Customer customer = customerRepository.findById(customerSsn).orElse(null);
        if (customer == null) {
            customer = new Customer();
            customer.setCustomerSsn(customerSsn);
            customer.setCustomerName(customerName);
            customer.setBirthDate(LocalDate.now());
            customerRepository.save(customer);
        }

        // 2️⃣ 계좌 확인
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found: " + accountId));

        // 3️⃣ 카드 ID 생성
        String lastCardId = cardRepository.findTopByOrderByCardIdDesc();
        int nextId = 10001; // 최초 시작값

        if (lastCardId != null && lastCardId.startsWith("C")) {
            nextId = Integer.parseInt(lastCardId.substring(1)) + 1;
        }

        // 4️⃣ 신규 카드 생성
        Card newCard = new Card();
        newCard.setCardId("C" + nextId);
        newCard.setCustomer(customer);
        newCard.setAccount(account);
        newCard.setCardType(cardType);
        newCard.setIssueDate(LocalDate.now());
        newCard.setPaymentDate(25);
        newCard.setLimitAmount(BigDecimal.valueOf(10_000_000));

        return cardRepository.save(newCard);
    }
}
