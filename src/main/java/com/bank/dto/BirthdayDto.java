package com.bank.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BirthdayDto {
    private String customerName;
    private String address;
    private LocalDate birthDate;
    private long dday;
}
