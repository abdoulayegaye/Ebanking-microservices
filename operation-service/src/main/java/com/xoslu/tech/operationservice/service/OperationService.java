package com.xoslu.tech.operationservice.service;

import com.xoslu.tech.operationservice.dto.AccountDTO;
import com.xoslu.tech.operationservice.dto.OperationDTO;
import com.xoslu.tech.operationservice.entity.Operation;
import com.xoslu.tech.operationservice.enums.TypeOperation;
import com.xoslu.tech.operationservice.feign.AccountClient;
import com.xoslu.tech.operationservice.repository.OperationRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OperationService {
    private final OperationRepository operationRepository;
    private final AccountClient accountClient;

    public OperationDTO deposit(Operation operation) {
        AccountDTO accountDTO = accountClient.getAccount(operation.getAccountNumber());
        operation.setType(TypeOperation.DEPOSIT);
        operation.setNumero(UUID.randomUUID().toString());
        operation.setCreatedAt(Instant.now());
        operation.setAccountNumber(accountDTO.getNumero());
        operationRepository.save(operation);
        return toDTO(operation);
    }

    public OperationDTO withdraw(Operation operation) throws BadRequestException {
        AccountDTO accountDTO = accountClient.getAccount(operation.getAccountNumber());
        if (operation.getAmount() > accountDTO.getBalance())
            throw new BadRequestException("Solde insuffisant");
        operation.setType(TypeOperation.WITHDRAW);
        operation.setNumero(UUID.randomUUID().toString());
        operation.setCreatedAt(Instant.now());
        operation.setAccountNumber(accountDTO.getNumero());
        operationRepository.save(operation);
        return toDTO(operation);
    }

    public List<OperationDTO> getAllOperations() {
        return toDTOs(operationRepository.findAll());
    }

    private OperationDTO toDTO(Operation operation) {
        OperationDTO operationDTO = new OperationDTO();
        operationDTO.setId(operation.getId());
        operationDTO.setType(operation.getType());
        operationDTO.setAmount(operation.getAmount());
        operationDTO.setCreatedAt(operation.getCreatedAt());
        operationDTO.setNumero(operation.getNumero());
        operationDTO.setAccount(accountClient.getAccount(operation.getAccountNumber()));
        return operationDTO;
    }

    private List<OperationDTO> toDTOs(List<Operation> operations) {
        List<OperationDTO> operationDTOs = new ArrayList<>();
        for (Operation operation : operations) {
            operationDTOs.add(toDTO(operation));
        }
        return operationDTOs;
    }
}
