package com.bank.repository;

import com.bank.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.account.accountId = :accountId " +
            "AND t.transactionDate >= :startDate ORDER BY t.transactionDate DESC")
    List<Transaction> findLastMonth(@Param("accountId") Long accountId,
                                    @Param("startDate") LocalDateTime startDate);
}

