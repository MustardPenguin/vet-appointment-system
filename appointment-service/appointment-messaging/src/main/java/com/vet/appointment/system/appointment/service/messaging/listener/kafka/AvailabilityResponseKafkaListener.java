package com.vet.appointment.system.appointment.service.messaging.listener.kafka;

import com.vet.appointment.system.kafka.consumer.KafkaConsumer;
import debezium.availability.appointment_outbox.Envelope;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class AvailabilityResponseKafkaListener implements KafkaConsumer<Envelope> {

    @Override
    @KafkaListener(groupId = "${kafka-consumer-group-id.availability-group-id}",
            topics = "${kafka-consumer-topic-name.availability-response-event}")
    public void receive(@Payload List<Envelope> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {

        messages.forEach(avroModel -> {
            log.info(avroModel.getAfter().getPayload());
        });
    }
}
