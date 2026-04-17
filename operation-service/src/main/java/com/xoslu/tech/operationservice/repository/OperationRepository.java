package com.xoslu.tech.operationservice.repository;

import com.xoslu.tech.operationservice.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}
