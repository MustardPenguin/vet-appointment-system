package com.vet.appointment.system.appointment.service.messaging.listener.kafka;

import com.vet.appointment.system.appointment.service.domain.dto.message.PetModel;
import com.vet.appointment.system.appointment.service.domain.ports.input.message.listener.PetCreatedMessageListener;
import com.vet.appointment.system.kafka.avro.model.CreatePetEventAvroModel;
import com.vet.appointment.system.kafka.consumer.KafkaConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static com.vet.appointment.system.appointment.service.domain.config.AppointmentServiceDataConfig.CreatePetEventTopicName;

@Slf4j
@Component
public class PetCreatedEventKafkaListener implements KafkaConsumer<CreatePetEventAvroModel> {

    private final PetCreatedMessageListener petCreatedMessageListener;

    public PetCreatedEventKafkaListener(PetCreatedMessageListener petCreatedMessageListener) {
        this.petCreatedMessageListener = petCreatedMessageListener;
    }

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

        messages.forEach(createPetEventAvroModel ->
                petCreatedMessageListener.petCreated(PetModel.builder()
                                .id(UUID.fromString(createPetEventAvroModel.getId()))
                                .ownerId(UUID.fromString(createPetEventAvroModel.getOwnerId()))
                                .name(createPetEventAvroModel.getName())
                                .birthDate(LocalDate.parse(createPetEventAvroModel.getBirthDate()))
                                .species(createPetEventAvroModel.getSpecies())
                                .build()));
    }
}
