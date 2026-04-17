package com.xoslu.tech.operationservice.entity;

import com.xoslu.tech.operationservice.enums.TypeOperation;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "operations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private Instant createdAt;
    @Enumerated(EnumType.STRING)
    private TypeOperation type;
    private double amount;
    private String accountNumber;
}
