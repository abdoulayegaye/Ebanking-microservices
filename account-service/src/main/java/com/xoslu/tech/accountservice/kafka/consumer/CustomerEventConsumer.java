package com.xoslu.tech.accountservice.kafka.consumer;

import com.xoslu.tech.accountservice.kafka.events.CustomerCreatedEvent;
import com.xoslu.tech.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerEventConsumer {
    private final AccountService accountService;

    @KafkaListener(
            topics = "customer-events",
            groupId = "account-service-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void handleCustomerCreatedEvent(CustomerCreatedEvent event,
                                           @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                                           @Header(KafkaHeaders.OFFSET) long offset) {
        log.info("CustomerCreatedEvent recu -> customerId: {}, partition: {}, offset: {}", event.id(), partition, offset);
        try{
            accountService.createAccountKafka(event);
        }catch (Exception e){
            log.error("CustomerCreatedEvent recu -> customerId: {}, error: {}", event.id(), e.getMessage());
        }
    }
}
