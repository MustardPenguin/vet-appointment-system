package com.vet.appointment.system.availability.service.messaging.listener.kafka;

import com.vet.appointment.system.kafka.consumer.KafkaConsumer;
import debezium.appointment.availability_outbox.Envelope;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class AppointmentAvailabilityKafkaListener implements KafkaConsumer<Envelope> {


    @Override
    @KafkaListener(topics = "${kafka-consumer-topic.availability-request-topic-name}", groupId = "${kafka-consumer-group-id.availability-group-id}")
    public void receive(@Payload List<Envelope> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {

        messages.forEach(avroModel -> {
            log.info(avroModel.getAfter().getId());
        });
    }
}
