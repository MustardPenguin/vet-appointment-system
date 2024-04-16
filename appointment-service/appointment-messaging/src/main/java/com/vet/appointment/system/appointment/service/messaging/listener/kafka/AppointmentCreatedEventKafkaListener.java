package com.vet.appointment.system.appointment.service.messaging.listener.kafka;

import appointment_created.appointment.appointment_outbox.Envelope;
import appointment_created.appointment.appointment_outbox.Value;
import com.vet.appointment.system.appointment.service.domain.dto.outbox.AppointmentCreatedEventPayload;
import com.vet.appointment.system.appointment.service.domain.entity.Appointment;
import com.vet.appointment.system.appointment.service.domain.ports.input.message.listener.AppointmentCreatedMessageListener;
import com.vet.appointment.system.appointment.service.messaging.mapper.AppointmentMessagingDataMapper;
import com.vet.appointment.system.kafka.consumer.KafkaConsumer;
import com.vet.appointment.system.kafka.producer.KafkaMessageHelper;
import com.vet.appointment.system.messaging.DebeziumOp;
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
public class AppointmentCreatedEventKafkaListener implements KafkaConsumer<Envelope> {

    private final AppointmentCreatedMessageListener appointmentCreatedMessageListener;
    private final AppointmentMessagingDataMapper appointmentMessagingDataMapper;
    private final KafkaMessageHelper kafkaMessageHelper;

    public AppointmentCreatedEventKafkaListener(AppointmentCreatedMessageListener appointmentCreatedMessageListener,
                                                AppointmentMessagingDataMapper appointmentMessagingDataMapper,
                                                KafkaMessageHelper kafkaMessageHelper) {
        this.appointmentCreatedMessageListener = appointmentCreatedMessageListener;
        this.appointmentMessagingDataMapper = appointmentMessagingDataMapper;
        this.kafkaMessageHelper = kafkaMessageHelper;
    }

    @Override
    @KafkaListener(topics = "${kafka-consumer-topic-name.appointment-created-event}",
            groupId = "${applicationDeploymentName}-appointment-created-listener")
    public void receive(@Payload List<Envelope> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("Received appointment propagation event!");

        messages.forEach(message -> {
            if(message.getBefore() == null && message.getOp().equals(DebeziumOp.CREATE.getValue())) {
                Value avroModel = message.getAfter();
                AppointmentCreatedEventPayload appointmentCreatedEventPayload =
                        kafkaMessageHelper.getEventPayload(avroModel.getPayload(), AppointmentCreatedEventPayload.class);

                appointmentCreatedMessageListener.appointmentCreated(
                        appointmentMessagingDataMapper.appointmentCreatedEventPayloadToAppointment(appointmentCreatedEventPayload),
                        UUID.fromString(avroModel.getId()));
            }
        });
    }
}
