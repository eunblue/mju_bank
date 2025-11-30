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
    private BigDecimal limitAmount;      // 신용카드 한도
    private BigDecimal usedAmount;       // 사용 금액
    private BigDecimal accountBalance;   // 연결 계좌 잔액
    private Integer paymentDate;
    private String customerName;
    private String customerSsn;
    private Integer accountId;           // 연결 계좌 ID
}
