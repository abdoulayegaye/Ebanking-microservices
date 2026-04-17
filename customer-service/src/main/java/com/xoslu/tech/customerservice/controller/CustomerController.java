package com.xoslu.tech.customerservice.controller;

import com.xoslu.tech.customerservice.entity.Customer;
import com.xoslu.tech.customerservice.repository.CustomerRepository;
import com.xoslu.tech.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public Customer save(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @GetMapping
    public List<Customer> findAll() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer findById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }
}
