package com.xoslu.tech.operationservice.dto;

import com.xoslu.tech.operationservice.enums.TypeOperation;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OperationDTO {
    private Long id;
    private String numero;
    private Instant createdAt;
    private TypeOperation type;
    private double amount;
    private AccountDTO account;
}
