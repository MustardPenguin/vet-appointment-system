package com.vet.appointment.system.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vet.appointment.system.outbox.OutboxStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

@Slf4j
@Component
public class KafkaMessageHelper {

    private final ObjectMapper objectMapper;

    public KafkaMessageHelper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> T getEventPayload(String payload, Class<T> classType) {
        try {
            return objectMapper.readValue(payload, classType);
        } catch(JsonProcessingException e) {
            log.error("Could not read {} object!", classType.getName(), e);
            throw new RuntimeException("Could not read " + classType.getName() + " object!");
        }
    }

    public <T, U> BiConsumer<SendResult<String, T>, Throwable> getKafkaCallback(
            String topicName, T avroModel, U outboxMessage,
            BiConsumer<U, OutboxStatus> callback) {

        return (result, ex) -> {
            if(ex == null) {
                log.info("Received response from kafka for topic {}", topicName);
                callback.accept(outboxMessage, OutboxStatus.COMPLETED);
            } else {
                log.error("Failed while sending outbox message to topic {}", topicName);
                callback.accept(outboxMessage, OutboxStatus.FAILED);
            }
        };
    }
}
