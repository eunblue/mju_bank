package com.bank.service;

import com.bank.domain.Account;
import com.bank.domain.Transaction;
import com.bank.repository.AccountRepository;
import com.bank.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    // 거래 등록
    public Transaction createTransaction(Integer accountId, String type, String content, BigDecimal amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("계좌가 존재하지 않습니다."));

        BigDecimal newBalance = type.equalsIgnoreCase("입금")
                ? account.getBalance().add(amount)
                : account.getBalance().subtract(amount);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setTransactionType(type);
        transaction.setTransactionContent(content);
        transaction.setAmount(amount);
        transaction.setBalanceAfter(newBalance);
        transaction.setTransactionDate(LocalDateTime.now());

        account.setBalance(newBalance);
        accountRepository.save(account);

        return transactionRepository.save(transaction);
    }

    // 거래 내역 조회 (최근 1개월, 첫 계좌 기준)
    public List<Transaction> getTransactionsByFirstAccount(String customerName, String customerSsn) {
        List<Account> accounts = accountRepository
                .findByCustomerCustomerNameAndCustomerCustomerSsn(customerName, customerSsn);
        if (accounts.isEmpty()) return Collections.emptyList();

        Account firstAccount = accounts.get(0);
        LocalDateTime startDate = LocalDateTime.now().minusDays(30);

        return transactionRepository.findRecentMonthHistory(
                firstAccount.getAccountId(),
                customerSsn,
                customerName,
                startDate
        );
    }
}
