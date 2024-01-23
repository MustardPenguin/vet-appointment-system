package com.vet.appointment.system.kafka.config.data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kafka-consumer-data")
public class KafkaConsumerConfigData {

    private String keyDeserializer;
    private String valueDeserializer;

    public String getKeyDeserializer() {
        return keyDeserializer;
    }

    public String getValueDeserializer() {
        return valueDeserializer;
    }
}
