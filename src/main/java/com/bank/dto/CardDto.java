package com.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CardDto {
    private String cardId;
    private String cardType;
    private LocalDate issueDate;
    private BigDecimal limitAmount;
    private Integer paymentDate;
    private String customerName;
    private String customerSsn;
    private Integer accountId;


}
