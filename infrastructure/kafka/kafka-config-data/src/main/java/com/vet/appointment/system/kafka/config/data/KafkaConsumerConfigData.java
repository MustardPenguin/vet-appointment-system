package com.vet.appointment.system.kafka.config.data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kafka-consumer-data")
public class KafkaConsumerConfigData {

    private String keyDeserializer;
    private String valueDeserializer;

    private String specificAvroReader;
    private String specificAvroReaderKey;

    public String getSpecificAvroReader() {
        return specificAvroReader;
    }

    public void setSpecificAvroReader(String specificAvroReader) {
        this.specificAvroReader = specificAvroReader;
    }

    public String getSpecificAvroReaderKey() {
        return specificAvroReaderKey;
    }

    public void setSpecificAvroReaderKey(String specificAvroReaderKey) {
        this.specificAvroReaderKey = specificAvroReaderKey;
    }

    public String getKeyDeserializer() {
        return keyDeserializer;
    }

    public String getValueDeserializer() {
        return valueDeserializer;
    }

    public void setKeyDeserializer(String keyDeserializer) {
        this.keyDeserializer = keyDeserializer;
    }

    public void setValueDeserializer(String valueDeserializer) {
        this.valueDeserializer = valueDeserializer;
    }
}
