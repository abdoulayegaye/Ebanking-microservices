package com.xoslu.tech.customerservice.kafka.events;

public record CustomerCreatedEvent(
        Long id,
        String fullName,
        String email
) {}
