package com.bank.repository;

import com.bank.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account> findByCustomer_CustomerName(String name);

    List<Account> findByCustomer_CustomerNameOrderByOpenDate(String name);

    // 고객 이름으로 계좌 조회
    @Query("SELECT a FROM Account a JOIN a.customer c WHERE c.customerName = :customerName")
    List<Account> findByCustomerName(String customerName);

    // 고객 이름으로 계좌 조회 후 개설일 기준 정렬
    @Query("SELECT a FROM Account a JOIN a.customer c WHERE c.customerName = :customerName ORDER BY a.openDate ASC")
    List<Account> findByCustomerNameOrderByOpenDateAsc(String customerName);
}

