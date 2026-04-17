package com.xoslu.tech.customerservice.repository;

import com.xoslu.tech.customerservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
