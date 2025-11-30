package com.bank.service;

import com.bank.domain.Account;
import com.bank.domain.Card;
import com.bank.domain.Customer;
import com.bank.dto.CreditCardInfoDto;
import com.bank.repository.AccountRepository;
import com.bank.repository.CardRepository;
import com.bank.repository.CustomerRepository;
import com.bank.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final CardRepository cardRepository;
    private final TransactionRepository transactionRepository;

    // 카드 발급
    public synchronized Card issueCard(String customerSsn, String customerName, Integer accountId, String cardType) {
        Customer customer = customerRepository.findById(customerSsn).orElse(null);
        if (customer == null) {
            customer = new Customer();
            customer.setCustomerSsn(customerSsn);
            customer.setCustomerName(customerName);
            customer.setBirthDate(LocalDate.now());
            customerRepository.save(customer);
        }

        Account account = null;
        if (accountId != null) {
            account = accountRepository.findById(accountId)
                    .orElseThrow(() -> new RuntimeException("Account Not Found: " + accountId));
        }

        Card lastCard = cardRepository.findTopByOrderByCardIdDesc();
        int nextId = 10001;
        if (lastCard != null && lastCard.getCardId().startsWith("C")) {
            nextId = Integer.parseInt(lastCard.getCardId().substring(1)) + 1;
        }

        Card newCard = new Card();
        newCard.setCardId("C" + nextId);
        newCard.setCustomer(customer);
        newCard.setAccount(account);
        newCard.setCardType(cardType);
        newCard.setIssueDate(LocalDate.now());

        if ("체크".equals(cardType)) {
            newCard.setLimitAmount(BigDecimal.ZERO);
            newCard.setPaymentDate(0);
        } else { // 신용카드
            newCard.setLimitAmount(BigDecimal.valueOf(10_000_000));
            newCard.setPaymentDate(25);
        }

        return cardRepository.save(newCard);
    }

    @Transactional(readOnly = true)
    public List<CreditCardInfoDto> getCardsByCustomer(String customerSsn) {
        List<Card> cards = cardRepository.findByCustomerCustomerSsn(customerSsn);

        return cards.stream().map(card -> {
            Account account = card.getAccount();
            Integer accountId = account != null ? account.getAccountId() : null;
            String accountType = account != null ? account.getAccountType() : null;
            BigDecimal accountBalance = account != null ? account.getBalance() : BigDecimal.ZERO;

            BigDecimal usedAmount = BigDecimal.ZERO;
            if ("신용".equals(card.getCardType()) && accountId != null) {
                BigDecimal sumUsed = transactionRepository.sumUsedAmount(accountId);
                usedAmount = sumUsed != null ? sumUsed : BigDecimal.ZERO;
            }

            BigDecimal limitAmount = "신용".equals(card.getCardType()) ? card.getLimitAmount() : BigDecimal.ZERO;

            return new CreditCardInfoDto(
                    card.getCardId(),
                    card.getCardType(),
                    accountId,
                    accountType,
                    accountBalance,
                    usedAmount,
                    limitAmount,
                    card.getIssueDate()
            );
        }).collect(Collectors.toList());
    }
}
