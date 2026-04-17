package com.xoslu.tech.accountservice.controller;

import com.xoslu.tech.accountservice.dto.AccountDTO;
import com.xoslu.tech.accountservice.entity.Account;
import com.xoslu.tech.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public AccountDTO createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @GetMapping
    public List<AccountDTO> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public AccountDTO getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @GetMapping("/search/{numero}")
    public AccountDTO getAccountByNumero(@PathVariable String numero) {
        return accountService.getAccountByNumero(numero);
    }
}
