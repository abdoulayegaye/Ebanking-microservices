package com.xoslu.tech.accountservice.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AccountDTO {
    private Long id;
    private String numero;
    private Instant createdAt;
    private double balance;
    private String currency;
    private boolean active;
    private CustomerDTO customer;
}
