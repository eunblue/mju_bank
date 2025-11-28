package com.bank.controller;

import com.bank.domain.Card;
import com.bank.domain.Customer;
import com.bank.dto.AccountInfoDto;
import com.bank.dto.BirthdayDto;
import com.bank.repository.CustomerRepository;
import com.bank.service.AccountService;
import com.bank.service.CardService;
import com.bank.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final CardService cardService;
    private final CustomerService customerService;


    // -------------------------
    // 계좌 생성 페이지
    // -------------------------
    @GetMapping("/new")
    public String newAccountPage() {
        return "account_new"; // templates/account_new.html
    }

    // -------------------------
    // 계좌 생성 API
    // -------------------------
    @PostMapping("/create")
    @ResponseBody
    public Object createAccount(
            @RequestParam String customerSsn,
            @RequestParam String customerName,
            @RequestParam String accountType
    ) {
        return accountService.createAccount(customerSsn, customerName, accountType);
    }

    // -------------------------
    // 고객 이름 기준 계좌 조회 (고객별 그룹핑)
    // -------------------------
    @GetMapping("/by-name/grouped")
    @ResponseBody
    public Map<String, List<AccountInfoDto>> getAccountsGroupedByCustomer(@RequestParam String customerName) {
        return accountService.getAccountsGroupedByCustomer(customerName);
    }

    // -------------------------
    // 카드 발급 API
    // -------------------------
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

    @GetMapping("/next-birthday")
    @ResponseBody
    public BirthdayDto getUpcomingBirthdayCustomer() {
        return customerService.getUpcomingBirthdayCustomerDto();
    }

}
