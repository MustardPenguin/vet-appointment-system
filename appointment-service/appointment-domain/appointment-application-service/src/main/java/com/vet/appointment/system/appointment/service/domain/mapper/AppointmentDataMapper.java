package com.vet.appointment.system.appointment.service.domain.mapper;

import com.vet.appointment.system.appointment.service.domain.dto.create.CreateAppointmentCommand;
import com.vet.appointment.system.appointment.service.domain.entity.Appointment;
import com.vet.appointment.system.appointment.service.domain.event.AppointmentCreatedEvent;
import com.vet.appointment.system.appointment.service.domain.outbox.model.AppointmentAvailabilityEventPayload;
import com.vet.appointment.system.domain.valueobject.AppointmentStatus;
import com.vet.appointment.system.domain.valueobject.PaymentStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AppointmentDataMapper {

    public Appointment createAppointmentCommandToAppointment(CreateAppointmentCommand createAppointmentCommand) {
        return Appointment.builder()
                .id(UUID.randomUUID())
                .appointmentStartDateTime(createAppointmentCommand.getAppointmentStartDateTime())
                .appointmentEndDateTime(createAppointmentCommand.getAppointmentEndDateTime())
                .ownerId(createAppointmentCommand.getOwnerId())
                .petId(createAppointmentCommand.getPetId())
                .description(createAppointmentCommand.getDescription())
                .appointmentStatus(AppointmentStatus.REQUESTING)
                .paymentStatus(PaymentStatus.PENDING)
                .build();
    }

    public AppointmentAvailabilityEventPayload appointmentEventToEventPayload(AppointmentCreatedEvent appointmentCreatedEvent) {
        return new AppointmentAvailabilityEventPayload(
                appointmentCreatedEvent.getEntity().getId().getValue(),
                appointmentCreatedEvent.getEntity().getAppointmentStartDateTime(),
                appointmentCreatedEvent.getEntity().getAppointmentEndDateTime(),
                appointmentCreatedEvent.getCreatedAt());
    }
}