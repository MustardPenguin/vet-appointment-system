package com.vet.appointment.system.appointment.service.domain.helper;

import com.vet.appointment.system.appointment.service.domain.dto.outbox.AppointmentCreatedEventPayload;
import com.vet.appointment.system.appointment.service.domain.dto.outbox.AppointmentOutboxMessage;
import com.vet.appointment.system.appointment.service.domain.entity.Appointment;
import com.vet.appointment.system.appointment.service.domain.event.AppointmentEvent;
import com.vet.appointment.system.appointment.service.domain.ports.output.repository.outbox.AppointmentOutboxRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class AppointmentOutboxHelper {

    private final AppointmentOutboxRepository appointmentOutboxRepository;
    private final OutboxHelper<AppointmentCreatedEventPayload> outboxHelper;

    public AppointmentOutboxHelper(AppointmentOutboxRepository appointmentOutboxRepository,
                                   OutboxHelper<AppointmentCreatedEventPayload> outboxHelper) {
        this.appointmentOutboxRepository = appointmentOutboxRepository;
        this.outboxHelper = outboxHelper;
    }

    @Transactional
    public void save(AppointmentOutboxMessage appointmentOutboxMessage) {
        AppointmentOutboxMessage response = appointmentOutboxRepository.save(appointmentOutboxMessage);
        if(response == null) {
            log.error("Could not save appointment outbox message with id: {}!", appointmentOutboxMessage.getId());
            throw new RuntimeException("Could not save appointment outbox message with id: " + appointmentOutboxMessage.getId());
        }
        log.info("Successfully saved appointment outbox message with id: {}!", appointmentOutboxMessage.getId());
    }

    @Transactional
    public void saveAppointmentOutboxMessage(AppointmentEvent appointmentEvent) {
        Appointment appointment = appointmentEvent.getEntity();
        String payload = outboxHelper.createPayload(appointmentCreatedEventPayload(appointment), appointment.getId().getValue());
        AppointmentOutboxMessage appointmentOutboxMessage = AppointmentOutboxMessage.builder()
                .id(appointment.getId().getValue())
                .createdAt(appointmentEvent.getCreatedAt())
                .payload(payload)
                .build();
        save(appointmentOutboxMessage);
    }

    private AppointmentCreatedEventPayload appointmentCreatedEventPayload(Appointment appointment) {
        return AppointmentCreatedEventPayload.builder()
                .id(appointment.getId().getValue())
                .appointmentStartDateTime(appointment.getAppointmentStartDateTime())
                .appointmentEndDateTime(appointment.getAppointmentEndDateTime())
                .description(appointment.getDescription())
                .cost(appointment.getCost())
                .appointmentStatus(appointment.getAppointmentStatus())
                .paymentStatus(appointment.getPaymentStatus())
                .errorMessages(appointment.getErrorMessages())
                .ownerId(appointment.getOwnerId())
                .petId(appointment.getPetId())
                .availabilityId(appointment.getAvailabilityId())
                .paymentId(appointment.getPaymentId())
                .build();
    }
}
