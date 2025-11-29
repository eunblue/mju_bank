package com.bank.repository;

import com.bank.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findByCustomer_CustomerName(String name);

    Card findTopByOrderByCardIdDesc();

    List<Card> findByCustomerCustomerSsnAndCardType(String customerSsn, String 신용);
}

