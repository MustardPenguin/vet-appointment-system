package com.vet.appointment.system.kafka.config.data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kafka-producer-data")
public class KafkaProducerConfigData {

    private String keySerializerClass;
    private String valueSerializerClass;

    public String getKeySerializerClass() {
        return keySerializerClass;
    }

    public String getValueSerializerClass() {
        return valueSerializerClass;
    }
}
