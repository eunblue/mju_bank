package com.bank.dto;

import java.math.BigDecimal;

public record CreditCardInfoDto(
        String cardId,      // Card.cardId는 String
        Integer accountId,  // Account.accountId는 Integer
        String accountType,
        BigDecimal usedAmount,   // 계산 필요
        BigDecimal limitAmount
) {}

