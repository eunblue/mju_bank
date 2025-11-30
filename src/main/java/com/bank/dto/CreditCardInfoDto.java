package com.bank.dto;

import java.math.BigDecimal;

public class CreditCardInfoDto {

    private String cardId;
    private String cardType;
    private Integer accountId;
    private String accountType;
    private BigDecimal usedAmount;
    private BigDecimal limitAmount;
    private BigDecimal accountBalance;

    public CreditCardInfoDto(String cardId, String cardType, Integer accountId, String accountType,
                             BigDecimal usedAmount, BigDecimal limitAmount, BigDecimal accountBalance) {
        this.cardId = cardId;
        this.cardType = cardType;
        this.accountId = accountId;
        this.accountType = accountType;
        this.usedAmount = usedAmount;
        this.limitAmount = limitAmount;
        this.accountBalance = accountBalance;
    }

    // Getter/Setter
    public String getCardId() { return cardId; }
    public void setCardId(String cardId) { this.cardId = cardId; }
    public String getCardType() { return cardType; }
    public void setCardType(String cardType) { this.cardType = cardType; }
    public Integer getAccountId() { return accountId; }
    public void setAccountId(Integer accountId) { this.accountId = accountId; }
    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }
    public BigDecimal getUsedAmount() { return usedAmount; }
    public void setUsedAmount(BigDecimal usedAmount) { this.usedAmount = usedAmount; }
    public BigDecimal getLimitAmount() { return limitAmount; }
    public void setLimitAmount(BigDecimal limitAmount) { this.limitAmount = limitAmount; }
    public BigDecimal getAccountBalance() { return accountBalance; }
    public void setAccountBalance(BigDecimal accountBalance) { this.accountBalance = accountBalance; }
}
