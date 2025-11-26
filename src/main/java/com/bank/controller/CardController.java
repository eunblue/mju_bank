package com.bank.controller;

import com.bank.domain.Card;
import com.bank.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/card")
public class CardController {

    private final CardService cardService;

    // 카드 생성 페이지
    @GetMapping("/new")
    public String newCardPage() {
        return "card_new"; // templates/card_new.html
    }

    // 카드 발급 API
    @PostMapping("/issue")
    @ResponseBody
    public Card issueCard(
            @RequestParam String customerSsn,
            @RequestParam String customerName,
            @RequestParam Integer accountId,
            @RequestParam String cardType
    ){
        return cardService.issueCard(customerSsn, customerName, accountId, cardType);
    }
}
