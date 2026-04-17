package com.xoslu.tech.operationservice.controller;

import com.xoslu.tech.operationservice.dto.OperationDTO;
import com.xoslu.tech.operationservice.entity.Operation;
import com.xoslu.tech.operationservice.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/operations")
@RequiredArgsConstructor
public class OperationController {
    private final OperationService operationService;

    @PostMapping("/deposit")
    public OperationDTO deposit(@RequestBody Operation operation) {
        return operationService.deposit(operation);
    }

    @PostMapping("/withdraw")
    public OperationDTO withdraw(@RequestBody Operation operation) throws BadRequestException {
        return operationService.withdraw(operation);
    }

    @GetMapping
    public List<OperationDTO> getAllOperations() {
        return operationService.getAllOperations();
    }
}
