package com.vet.appointment.system.availability.service.messaging.listener.kafka;

import availability_request.appointment.availability_outbox.Envelope;
import availability_request.appointment.availability_outbox.Value;
import com.vet.appointment.system.availability.service.domain.dto.message.AvailabilityRequest;
import com.vet.appointment.system.availability.service.domain.ports.input.message.listener.AppointmentAvailabilityMessageListener;
import com.vet.appointment.system.availability.service.messaging.mapper.AvailabilityMessagingDataMapper;
import com.vet.appointment.system.domain.valueobject.AppointmentStatus;
import com.vet.appointment.system.kafka.consumer.KafkaConsumer;
import com.vet.appointment.system.kafka.producer.KafkaMessageHelper;
import com.vet.appointment.system.messaging.DebeziumOp;
import com.vet.appointment.system.messaging.event.AppointmentAvailabilityEventPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class AppointmentAvailabilityKafkaListener implements KafkaConsumer<Envelope> {

    private final AppointmentAvailabilityMessageListener appointmentAvailabilityMessageListener;
    private final AvailabilityMessagingDataMapper availabilityMessagingDataMapper;
    private final KafkaMessageHelper kafkaMessageHelper;

    public AppointmentAvailabilityKafkaListener(AppointmentAvailabilityMessageListener appointmentAvailabilityMessageListener,
                                                AvailabilityMessagingDataMapper availabilityMessagingDataMapper,
                                                KafkaMessageHelper kafkaMessageHelper) {
        this.appointmentAvailabilityMessageListener = appointmentAvailabilityMessageListener;
        this.availabilityMessagingDataMapper = availabilityMessagingDataMapper;
        this.kafkaMessageHelper = kafkaMessageHelper;
    }

    @Override
    @KafkaListener(topics = "${kafka-consumer-topic.availability-request-topic-name}", groupId = "${kafka-consumer-group-id.availability-group-id}")
    public void receive(@Payload List<Envelope> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("received appointment event");

        messages.forEach(avroModel -> {
            if(avroModel.getBefore() == null && avroModel.getOp().equals(DebeziumOp.CREATE.getValue())) {
                Value appointmentAvailabilityAvroModel = avroModel.getAfter();
                AppointmentAvailabilityEventPayload appointmentAvailabilityEventPayload =
                        kafkaMessageHelper.getEventPayload(appointmentAvailabilityAvroModel.getPayload(), AppointmentAvailabilityEventPayload.class);
                log.info("Received appointment availability event for id: {} with appointment status: {}",
                        appointmentAvailabilityEventPayload.getId(), appointmentAvailabilityEventPayload.getAppointmentStatus());

                AvailabilityRequest availabilityRequest = availabilityMessagingDataMapper
                        .availabilityEventPayloadToAvailabilityRequest(appointmentAvailabilityEventPayload, UUID.fromString(appointmentAvailabilityAvroModel.getSagaId()));
                if(appointmentAvailabilityEventPayload.getAppointmentStatus() == AppointmentStatus.REQUESTING) {
                    appointmentAvailabilityMessageListener
                            .checkAvailability(availabilityRequest, appointmentAvailabilityEventPayload.getId());
                } else if(appointmentAvailabilityEventPayload.getAppointmentStatus() == AppointmentStatus.CANCELLING) {
                    appointmentAvailabilityMessageListener
                            .cancelAvailability(availabilityRequest, appointmentAvailabilityEventPayload.getId());
                }
            }
        });
    }
}
