package com.vet.appointment.system.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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


}
