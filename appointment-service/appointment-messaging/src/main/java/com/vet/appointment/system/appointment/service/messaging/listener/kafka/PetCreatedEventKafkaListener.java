package com.vet.appointment.system.appointment.service.messaging.listener.kafka;

import com.vet.appointment.system.appointment.service.domain.dto.message.PetModel;
import com.vet.appointment.system.appointment.service.domain.ports.input.message.listener.PetCreatedMessageListener;
import com.vet.appointment.system.kafka.consumer.KafkaConsumer;
import com.vet.appointment.system.kafka.producer.KafkaMessageHelper;
import com.vet.appointment.system.messaging.DebeziumOp;
import com.vet.appointment.system.messaging.event.PetCreatedEventPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import pet_created.pet.appointment_outbox.Envelope;
import pet_created.pet.appointment_outbox.Value;

import java.util.List;
import java.util.UUID;


@Slf4j
@Component
public class PetCreatedEventKafkaListener implements KafkaConsumer<Envelope> {

    private final PetCreatedMessageListener petCreatedMessageListener;
    private final KafkaMessageHelper kafkaMessageHelper;

    public PetCreatedEventKafkaListener(PetCreatedMessageListener petCreatedMessageListener,
                                        KafkaMessageHelper kafkaMessageHelper) {
        this.petCreatedMessageListener = petCreatedMessageListener;
        this.kafkaMessageHelper = kafkaMessageHelper;
    }

    @Override
    @KafkaListener(topics = "${kafka-consumer-topic-name.pet-created-event}",
            groupId = "pet-created-listener-#{T(java.util.UUID).randomUUID().toString()}")
    public void receive(@Payload List<Envelope> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("Received kafka event message!");
        log.info("Messages: {}", messages);
        log.info("Key: {}", keys);
        log.info("Partitions: {}", partitions);
        log.info("Offsets: {}", offsets);

        messages.forEach(avroModel -> {
            if(avroModel.getBefore() == null && avroModel.getOp().equals(DebeziumOp.CREATE.getValue())) {
                Value petEventAvroModel = avroModel.getAfter();
                PetCreatedEventPayload petCreatedEventPayload = kafkaMessageHelper
                        .getEventPayload(petEventAvroModel.getPayload(), PetCreatedEventPayload.class);
                petCreatedMessageListener.petCreated(PetModel.builder()
                                .id(UUID.fromString(petCreatedEventPayload.getId()))
                                .ownerId(UUID.fromString(petCreatedEventPayload.getOwnerId()))
                                .name(petCreatedEventPayload.getName())
                                .species(petCreatedEventPayload.getSpecies())
                                .birthDate(petCreatedEventPayload.getBirthDate())
                                .build());
            }
        });
    }
}
