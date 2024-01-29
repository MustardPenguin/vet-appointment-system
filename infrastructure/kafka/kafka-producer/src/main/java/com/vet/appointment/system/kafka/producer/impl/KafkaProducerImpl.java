package com.vet.appointment.system.kafka.producer.impl;

import com.vet.appointment.system.kafka.producer.KafkaProducer;
import com.vet.appointment.system.kafka.producer.exception.KafkaProducerException;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

@Slf4j
@Component
public class KafkaProducerImpl<K extends Serializable, V extends SpecificRecordBase> implements KafkaProducer<K, V> {

    private final KafkaTemplate<K, V> kafkaTemplate;

    public KafkaProducerImpl(KafkaTemplate<K, V> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(String topicName, K key, V message,
                     BiConsumer<SendResult<K, V>, Throwable> callback) {
        try {
            CompletableFuture<SendResult<K, V>> kafkaResultFuture = kafkaTemplate.send(
                    topicName, key, message);
            kafkaResultFuture.whenComplete(callback);
        } catch(KafkaException e) {
            log.error("Error on kafka producer! Error: {}", e.getMessage());
            throw new KafkaProducerException("Error on kafka producer!");
        }
    }
}
