package com.bank.repository;

import com.bank.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, String> {

    Card findTopByOrderByCardIdDesc();

    List<Card> findByCustomerCustomerSsnAndCustomerCustomerNameAndCardType(
            String customerSsn,
            String customerName,
            String cardType
    );

    // CardRepository.java
    List<Card> findByCustomerCustomerSsn(String customerSsn);



    List<Card> findByCustomerCustomerSsnAndCardType(String customerSsn, String cardType);
}
