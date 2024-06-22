package com.urooms.management.events.service;

import com.urooms.management.managementMicroservice.domain.events.RentalCreateEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, RentalCreateEvent> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String,RentalCreateEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishRentalCreatedEvent(RentalCreateEvent eventData) {
        kafkaTemplate.send("Management-events", eventData);
    }
}