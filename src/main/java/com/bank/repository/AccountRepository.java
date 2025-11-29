package com.bank.repository;

import com.bank.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    // 이름 + SSN으로 계좌 조회
    List<Account> findByCustomerCustomerNameAndCustomerCustomerSsn(String customerName, String customerSsn);

    // 이름 기준 계좌 조회 후 개설일 기준 정렬
    List<Account> findByCustomerCustomerNameOrderByOpenDateAsc(String customerName);
}
