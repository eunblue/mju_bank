package com.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class AccountInfoDto {
    private String customerName;
    private String customerSsn;
    private Integer accountId;
    private BigDecimal balance;
    private String accountType;
}
