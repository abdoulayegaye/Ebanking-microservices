package com.xoslu.tech.accountservice.service;

import com.xoslu.tech.accountservice.dto.AccountDTO;
import com.xoslu.tech.accountservice.dto.CustomerDTO;
import com.xoslu.tech.accountservice.entity.Account;
import com.xoslu.tech.accountservice.feign.CustomerClient;
import com.xoslu.tech.accountservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerClient customerClient;

    public AccountDTO createAccount(Account account) {
        CustomerDTO customerDTO = customerClient.getCustomer(account.getCustomerId());
        account.setActive(Boolean.TRUE);
        account.setNumero(UUID.randomUUID().toString());
        account.setCurrency("XOF");
        account.setCustomerId(customerDTO.getId());
        account.setCreatedAt(Instant.now());
        account.setUpdatedAt(Instant.now());
        accountRepository.save(account);
        return toDTO(account);
    }

    public AccountDTO getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElse(null);
        assert account != null;
        return toDTO(account);
    }

    public AccountDTO getAccountByNumero(String numero) {
        Account account = accountRepository.findByNumero(numero).orElse(null);
        assert account != null;
        return toDTO(account);
    }

    public List<AccountDTO> getAllAccounts() {
        return toDTOs(accountRepository.findAll());
    }

    private AccountDTO toDTO(Account account) {
        AccountDTO dto = new AccountDTO();
        dto.setId(account.getId());
        dto.setNumero(account.getNumero());
        dto.setBalance(account.getBalance());
        dto.setCurrency(account.getCurrency());
        dto.setActive(account.isActive());
        dto.setCreatedAt(account.getCreatedAt());
        dto.setCustomer(customerClient.getCustomer(account.getCustomerId()));
        return dto;
    }

    private List<AccountDTO> toDTOs(List<Account> accounts) {
        List<AccountDTO> dtos = new ArrayList<>();
        for (Account account : accounts) {
            dtos.add(toDTO(account));
        }
        return dtos;
    }
}
