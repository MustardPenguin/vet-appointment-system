package com.vet.appointment.system.appointment.service.messaging.listener.kafka;

import com.vet.appointment.system.appointment.service.domain.ports.input.message.listener.AvailabilityResponseMessageListener;
import com.vet.appointment.system.domain.valueobject.AppointmentStatus;
import com.vet.appointment.system.kafka.consumer.KafkaConsumer;
import com.vet.appointment.system.kafka.producer.KafkaMessageHelper;
import com.vet.appointment.system.messaging.DebeziumOp;
import com.vet.appointment.system.messaging.event.AvailabilityAppointmentEventPayload;
import debezium.availability.appointment_outbox.Envelope;
import debezium.availability.appointment_outbox.Value;
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

    private final AvailabilityResponseMessageListener availabilityResponseMessageListener;
    private final KafkaMessageHelper kafkaMessageHelper;

    public AvailabilityResponseKafkaListener(AvailabilityResponseMessageListener availabilityResponseMessageListener,
                                             KafkaMessageHelper kafkaMessageHelper) {
        this.availabilityResponseMessageListener = availabilityResponseMessageListener;
        this.kafkaMessageHelper = kafkaMessageHelper;
    }

    @Override
    @KafkaListener(groupId = "${kafka-consumer-group-id.availability-group-id}",
            topics = "${kafka-consumer-topic-name.availability-response-event}")
    public void receive(@Payload List<Envelope> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {

        messages.forEach(avroModel -> {
            if(avroModel.getOp().equals(DebeziumOp.CREATE.getValue())) {
                Value availabilityResponseAvroModel = avroModel.getAfter();
                AvailabilityAppointmentEventPayload availabilityAppointmentEventPayload = kafkaMessageHelper
                        .getEventPayload(availabilityResponseAvroModel.getPayload(), AvailabilityAppointmentEventPayload.class);
                log.info("Received availability response event for appointment id: {} and saga id: {}",
                        availabilityAppointmentEventPayload.getAppointmentId(), availabilityResponseAvroModel.getSagaId());

                if(availabilityAppointmentEventPayload.getAppointmentStatus() == AppointmentStatus.AVAILABLE) {
                    availabilityResponseMessageListener.appointmentAvailable(availabilityAppointmentEventPayload);
                } else {
                    availabilityResponseMessageListener.appointmentUnavailable(availabilityAppointmentEventPayload);
                }
            }
        });
    }
}
