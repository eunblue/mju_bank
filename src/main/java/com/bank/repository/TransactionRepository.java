package com.bank.repository;

import com.bank.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // 기존: 최근 30일 내역 등은 이미 있으시면 유지하세요 (이 예시는 합계 메서드 추가)
    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t " +
            "WHERE t.account.accountId = :accountId AND t.transactionType = :type")
    BigDecimal sumAmountByAccountIdAndType(@Param("accountId") Integer accountId,
                                           @Param("type") String type);

    // 전체 합계(타입 무관)
    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t " +
            "WHERE t.account.accountId = :accountId")
    BigDecimal sumAmountByAccountId(@Param("accountId") Integer accountId);

    @Query("""
           SELECT t FROM Transaction t
           WHERE t.account.accountId = :accountId
           AND t.account.customer.customerSsn = :customerSsn
           AND t.account.customer.customerName = :customerName
           AND t.transactionDate >= :startDate
           ORDER BY t.transactionDate DESC
           """)
    List<Transaction> findRecentMonthHistory(
            @Param("accountId") Integer accountId,
            @Param("customerSsn") String customerSsn,
            @Param("customerName") String customerName,
            @Param("startDate") LocalDateTime startDate
    );

    // TransactionRepository.java
    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t " +
            "WHERE t.account.accountId = :accountId AND t.transactionType = '출금'")
    BigDecimal sumUsedAmount(@Param("accountId") Integer accountId);

}
