package com.vet.appointment.system.pet.service.messaging.listener.kafka;

import com.vet.appointment.system.kafka.consumer.KafkaConsumer;
import com.vet.appointment.system.kafka.producer.KafkaMessageHelper;
import com.vet.appointment.system.messaging.DebeziumOp;
import com.vet.appointment.system.messaging.event.PetCreatedEventPayload;
import com.vet.appointment.system.pet.service.domain.ports.input.message.listener.PetCreatedMessageListener;
import com.vet.appointment.system.pet.service.messaging.mapper.PetMessagingDataMapper;
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
    private final PetMessagingDataMapper petMessagingDataMapper;
    private final KafkaMessageHelper kafkaMessageHelper;

    public PetCreatedEventKafkaListener(PetCreatedMessageListener petCreatedMessageListener,
                                        PetMessagingDataMapper petMessagingDataMapper,
                                        KafkaMessageHelper kafkaMessageHelper) {
        this.petCreatedMessageListener = petCreatedMessageListener;
        this.petMessagingDataMapper = petMessagingDataMapper;
        this.kafkaMessageHelper = kafkaMessageHelper;
    }

    @Override
    @KafkaListener(topics = "${kafka-consumer-topic-name.pet-created-event}",
            groupId = "${applicationDeploymentName}-pet-created-listener")
    public void receive(@Payload List<Envelope> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("Received pet created event!");
        messages.forEach(message -> {
            if(message.getBefore() == null && message.getOp().equals(DebeziumOp.CREATE.getValue())) {
                Value avroModel = message.getAfter();
                PetCreatedEventPayload petCreatedEventPayload =
                        kafkaMessageHelper.getEventPayload(avroModel.getPayload(), PetCreatedEventPayload.class);
                if(petCreatedEventPayload.getPropagationType() != null && petCreatedEventPayload.getPropagationType().equals("D")) {
                    petCreatedMessageListener.petDeleted(
                            petMessagingDataMapper.petCreatedEventPayloadToPet(petCreatedEventPayload),
                            UUID.fromString(avroModel.getId()));
                    return;
                }

                petCreatedMessageListener.petCreated(
                        petMessagingDataMapper.petCreatedEventPayloadToPet(petCreatedEventPayload),
                        UUID.fromString(avroModel.getId()));
            }
        });
    }
}
