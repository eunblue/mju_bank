package com.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardInfoDto {
    private String cardId;
    private String cardType;
    private Integer accountId;
    private String accountType;
    private BigDecimal accountBalance;
    private BigDecimal usedAmount;
    private BigDecimal limitAmount;
    private LocalDate issueDate;
}
