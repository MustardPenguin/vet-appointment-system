package com.vet.appointment.system.availability.service.dataaccess.outbox.appointment.mapper;

import com.vet.appointment.system.availability.service.dataaccess.outbox.appointment.entity.AppointmentOutboxEntity;
import com.vet.appointment.system.availability.service.domain.dto.outbox.AvailabilityAppointmentOutboxMessage;
import org.springframework.stereotype.Component;

@Component
public class AppointmentDataAccessMapper {

    public AppointmentOutboxEntity appointmentOutboxMessageToOutboxEntity(AvailabilityAppointmentOutboxMessage availabilityAppointmentOutboxMessage) {
        return AppointmentOutboxEntity.builder()
                .id(availabilityAppointmentOutboxMessage.getId())
                .sagaId(availabilityAppointmentOutboxMessage.getSagaId())
                .sagaType(availabilityAppointmentOutboxMessage.getSagaType())
                .payload(availabilityAppointmentOutboxMessage.getPayload())
                .createdAt(availabilityAppointmentOutboxMessage.getCreatedAt())
                .version(availabilityAppointmentOutboxMessage.getVersion())
                .build();
    }

    public AvailabilityAppointmentOutboxMessage outboxEntityToAppointmentOutboxMessage(AppointmentOutboxEntity appointmentOutboxEntity) {
        return AvailabilityAppointmentOutboxMessage.builder()
                .id(appointmentOutboxEntity.getId())
                .sagaId(appointmentOutboxEntity.getSagaId())
                .sagaType(appointmentOutboxEntity.getSagaType())
                .payload(appointmentOutboxEntity.getPayload())
                .createdAt(appointmentOutboxEntity.getCreatedAt())
                .version(appointmentOutboxEntity.getVersion())
                .build();
    }
}
