package com.xoslu.tech.accountservice.kafka.events;

public record CustomerCreatedEvent(
        Long id,
        String fullName,
        String email
) {}
