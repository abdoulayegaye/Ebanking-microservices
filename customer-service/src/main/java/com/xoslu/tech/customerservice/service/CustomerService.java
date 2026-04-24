package com.xoslu.tech.customerservice.service;

import com.xoslu.tech.customerservice.entity.Customer;
import com.xoslu.tech.customerservice.kafka.events.CustomerCreatedEvent;
import com.xoslu.tech.customerservice.kafka.producer.CustomerEventProducer;
import com.xoslu.tech.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerEventProducer customerEventProducer;

    public Customer createCustomer(Customer customer) {
        // 1. Sauvegarder le customer
        Customer c = customerRepository.save(customer);
        // 2. Publier l'évnement Kafka
        customerEventProducer.sendCustomerCreatedEvent(
                new CustomerCreatedEvent(
                        c.getId(),
                        c.getFullName(),
                        c.getEmail()
                )
        );
        return c;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }
}
