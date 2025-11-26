package com.bank.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "card")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Id
    @Column(name = "card_id", nullable = false, length = 20)
    private String cardId;

    @Column(name = "issue_date", nullable = false)
    private LocalDate issueDate;

    @Column(name = "limit_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal limitAmount;

    @Column(name = "payment_date", nullable = false)
    private Integer paymentDate;

    @Column(name = "card_type", nullable = false)
    private String cardType;

    @ManyToOne
    @JoinColumn(name = "customer_ssn", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
}
