package com.xoslu.tech.customerservice.kafka.producer;

import com.xoslu.tech.customerservice.kafka.events.CustomerCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerEventProducer {

    private final KafkaTemplate<String, CustomerCreatedEvent> kafkaTemplate;
    private static final String TOPIC = "customer-events";

    public void sendCustomerCreatedEvent(CustomerCreatedEvent event) {
        kafkaTemplate.send(TOPIC, String.valueOf(event.id()), event)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("Erreur d'envoi d'événement customer d'ID-{} : {}", event.id(), ex.getMessage());
                    } else {
                        log.info("CustomerCreatedEvent envoyé -> partition: {}, offset: {}",
                                result.getRecordMetadata().partition(),
                                result.getRecordMetadata().offset());
                    }
                });
    }
}
