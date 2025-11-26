package com.bank.controller;

import com.bank.domain.Account;
import com.bank.domain.Card;
import com.bank.domain.Customer;
import com.bank.domain.Transaction;
import com.bank.service.QueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/query")
public class QueryController {

    private final QueryService queryService;

    // 화면 열기
    @GetMapping
    public String queryPage() {
        return "query_page";
    }

    // 1번: 고객 계좌 + 잔액 (선택적으로 정렬)
    @GetMapping("/api/accounts")
    @ResponseBody
    public List<Account> getAccounts(@RequestParam String name,
                                     @RequestParam(required = false) Boolean sort) {
        if (sort != null && sort) {
            return queryService.getAccountsByNameSorted(name);
        }
        return queryService.getAccountsByName(name);
    }

    // 3번: 과거 한달 거래내역 (첫 번째 계좌 기준)
    @GetMapping("/api/transactions")
    @ResponseBody
    public List<Transaction> getTransactions(@RequestParam String name) {
        List<Account> accounts = queryService.getAccountsByNameSorted(name);
        if (accounts.isEmpty()) return List.of();
        Integer firstAccountId = accounts.get(0).getAccountId(); // ✅ 첫 번째 계좌 ID 가져오기
        return queryService.getTransactionsLastMonth(firstAccountId.longValue()); // 필요하면 Long으로 변환
    }

    // 4번: 카드 정보
    @GetMapping("/api/cards")
    @ResponseBody
    public List<Card> getCards(@RequestParam String name) {
        return queryService.getCardsByName(name);
    }

    // 5번: 생일 가장 가까운 고객
    @GetMapping("/api/upcoming-birthday")
    @ResponseBody
    public Customer getUpcomingBirthday() {
        return queryService.getClosestBirthday();
    }

    //6번: 계좌 생성
    @PostMapping("/api/create-account")
    @ResponseBody
    public Account createAccount(@RequestParam Integer customerId,
                                 @RequestParam String accountType,
                                 @RequestParam(required = false, defaultValue = "false") Boolean cardRequest) {

        return queryService.createAccount(customerId, accountType, cardRequest);
    }

}
