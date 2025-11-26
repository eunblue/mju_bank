package com.bank.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @Column(name = "customer_ssn", nullable = false, length = 20)
    private String customerSsn; // char 타입, PK

    @Column(name = "customer_name", nullable = false, length = 50)
    private String customerName;

    @Column(name = "address")
    private String address;

    @Column(name = "birth_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_num")
    private String phoneNum;

    @Column(name = "occupation")
    private String occupation;

    // Lombok @Data가 Getter/Setter를 자동으로 생성하므로 아래는 삭제
    // public void setName(String name) {}
}
