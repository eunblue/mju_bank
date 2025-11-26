package com.bank.controller;

import com.bank.domain.Account;
import com.bank.domain.Card;
import com.bank.dto.AccountInfoDto;
import com.bank.service.AccountService;
import com.bank.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/bank")
public class BankController {

    private final AccountService accountService;
    private final CardService cardService;

    // 통합 페이지
    @GetMapping("/dashboard")
    public String dashboardPage() {
        return "dashboard"; // templates/dashboard.html
    }

    // 계좌 조회 (이름 기준, 개설일 순)
    @GetMapping("/accounts")
    @ResponseBody
    public List<AccountInfoDto> getAccounts(@RequestParam String customerName) {
        return accountService.getAccountsByCustomerNameSorted(customerName);
    }

    // 카드 발급
    @PostMapping("/card/issue")
    @ResponseBody
    public Card issueCard(
            @RequestParam String customerSsn,
            @RequestParam String customerName,
            @RequestParam Integer accountId,
            @RequestParam String cardType
    ) {
        return cardService.issueCard(customerSsn, customerName, accountId, cardType);
    }
}
