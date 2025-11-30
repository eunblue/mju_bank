package com.bank.repository;

import com.bank.domain.Card;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, String> {

    Card findTopByOrderByCardIdDesc();

    @EntityGraph(attributePaths = {"account"})
    List<Card> findByCustomerCustomerSsn(String customerSsn);
}
