package com.vet.appointment.system.pet.service.messaging.publisher.kafka;

import com.vet.appointment.system.kafka.avro.model.CreatePetEventAvroModel;
import com.vet.appointment.system.kafka.producer.KafkaMessageHelper;
import com.vet.appointment.system.kafka.producer.KafkaProducer;
import com.vet.appointment.system.kafka.producer.exception.KafkaProducerException;
import com.vet.appointment.system.outbox.OutboxStatus;
import com.vet.appointment.system.pet.service.domain.outbox.model.PetAppointmentEventPayload;
import com.vet.appointment.system.pet.service.domain.outbox.model.PetAppointmentOutboxMessage;
import com.vet.appointment.system.pet.service.domain.ports.output.message.publisher.PetCreatedMessagePublisher;
import com.vet.appointment.system.pet.service.messaging.mapper.PetMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

import static com.vet.appointment.system.pet.service.domain.config.PetServiceDataConfig.PetCreatedEventTopicName;

@Slf4j
@Component
public class PetCreatedEventKafkaPublisher implements PetCreatedMessagePublisher {

    private final PetMessagingDataMapper petMessagingDataMapper;
    private final KafkaMessageHelper kafkaMessageHelper;
    private final KafkaProducer<String, CreatePetEventAvroModel> kafkaProducer;

    public PetCreatedEventKafkaPublisher(PetMessagingDataMapper petMessagingDataMapper,
                                         KafkaMessageHelper kafkaMessageHelper,
                                         KafkaProducer<String, CreatePetEventAvroModel> kafkaProducer) {
        this.petMessagingDataMapper = petMessagingDataMapper;
        this.kafkaMessageHelper = kafkaMessageHelper;
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public void publish(PetAppointmentOutboxMessage petAppointmentOutboxMessage, BiConsumer<PetAppointmentOutboxMessage, OutboxStatus> outboxCallback) {

        PetAppointmentEventPayload petAppointmentEventPayload =
                kafkaMessageHelper.getEventPayload(petAppointmentOutboxMessage.getPayload(), PetAppointmentEventPayload.class);

        try {
            CreatePetEventAvroModel createPetEventAvroModel =
                    petMessagingDataMapper.eventPayloadToCreatePetAvroModel(petAppointmentEventPayload);

            BiConsumer<SendResult<String, CreatePetEventAvroModel>, Throwable> callback =
                    kafkaMessageHelper.getKafkaCallback(
                            PetCreatedEventTopicName,
                            createPetEventAvroModel,
                            petAppointmentOutboxMessage,
                            outboxCallback);
            kafkaProducer.send(
                    PetCreatedEventTopicName,
                    "",
                    createPetEventAvroModel,
                    callback);
        } catch(Exception e) {
            log.error("Error while sending PetAppointmentEventPayload to kafka with id: {}" +
                    " and error: {}", petAppointmentEventPayload.getId(), e.getMessage());
            throw new KafkaProducerException("Error while sending PetAppointmentEventPayload to kafka with id: " +
                    petAppointmentEventPayload.getId() + ", Error: " + e);
        }
    }
}
