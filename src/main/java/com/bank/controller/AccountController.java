package com.bank.controller;

import com.bank.dto.AccountInfoDto;
import com.bank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    // 계좌 생성 페이지
    @GetMapping("/new")
    public String newAccountPage() {
        return "account_new"; // templates/account_new.html
    }

    // 계좌 생성 API
    @PostMapping("/create")
    @ResponseBody
    public Object createAccount(
            @RequestParam String customerSsn,
            @RequestParam String customerName,
            @RequestParam String accountType
    ) {
        return accountService.createAccount(customerSsn, customerName, accountType);
    }

    // 이름으로 계좌 조회 (JSON 반환, DTO)
    @GetMapping("/by-name")
    @ResponseBody
    public List<AccountInfoDto> getAccountsByCustomerName(@RequestParam String customerName) {
        return accountService.getAccountsByCustomerName(customerName);
    }

    // 이름으로 계좌 조회 후 개설일 기준 정렬 (JSON 반환, DTO)
    @GetMapping("/by-name/sorted")
    @ResponseBody
    public List<AccountInfoDto> getAccountsByCustomerNameSorted(@RequestParam String customerName) {
        return accountService.getAccountsByCustomerNameSorted(customerName);
    }
}
