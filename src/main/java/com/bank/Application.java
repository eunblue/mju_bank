package com.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = "com.bank")               // ⭐ Bean 스캔
@EntityScan(basePackages = "com.bank.domain")            // ⭐ 엔티티 스캔
@EnableJpaRepositories(basePackages = "com.bank.repository") // ⭐ 리포 스캔
public class Application {

    public static void main(String[] args) {
        System.out.println("안녕하세요 은행입니다.");
        SpringApplication.run(Application.class, args);
    }
}
