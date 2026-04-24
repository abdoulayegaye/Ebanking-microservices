package com.xoslu.tech.customerservice.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

@Component
public class KafkaTopicConfig {

    @Bean
    public NewTopic customerTopic(){
        return TopicBuilder.name("customer-events")
                .partitions(3)
                .replicas(1)
                .build();
    }
}
