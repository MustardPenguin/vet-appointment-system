package com.vet.appointment.system.appointment.service.messaging.listener.kafka;

import com.vet.appointment.system.kafka.avro.model.CreatePetEventAvroModel;
import com.vet.appointment.system.kafka.consumer.KafkaConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.vet.appointment.system.appointment.service.domain.config.AppointmentServiceDataConfig.CreatePetEventTopicName;

@Slf4j
@Component
public class PetCreatedEventKafkaListener implements KafkaConsumer<CreatePetEventAvroModel> {

    @Override
    @KafkaListener(topics = CreatePetEventTopicName, groupId = "1")
    public void receive(@Payload List<CreatePetEventAvroModel> messages,
                        List<String> keys,
                        List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("Received kafka event message!");
        log.info("Messages: {}", messages);
        log.info("Key: {}", keys);
        log.info("Partitions: {}", partitions);
        log.info("Offsets: {}", offsets);

        messages.forEach(message ->
                System.out.println(message.getId()));
    }
}
