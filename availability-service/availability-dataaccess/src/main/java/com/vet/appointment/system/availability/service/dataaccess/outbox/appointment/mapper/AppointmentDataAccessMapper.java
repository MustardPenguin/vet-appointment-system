package com.vet.appointment.system.availability.service.dataaccess.outbox.appointment.mapper;

import com.vet.appointment.system.availability.service.domain.dto.outbox.AvailabilityAppointmentOutboxMessage;
import com.vet.appointment.system.dataaccess.outbox.appointment.entity.AppointmentOutboxEntity;
import org.springframework.stereotype.Component;

@Component
public class AppointmentDataAccessMapper {

    public AppointmentOutboxEntity appointmentOutboxMessageToOutboxEntity(AvailabilityAppointmentOutboxMessage availabilityAppointmentOutboxMessage) {
        return AppointmentOutboxEntity.builder()
                .id(availabilityAppointmentOutboxMessage.getId())
                .payload(availabilityAppointmentOutboxMessage.getPayload())
                .createdAt(availabilityAppointmentOutboxMessage.getCreatedAt())
                .outboxStatus(availabilityAppointmentOutboxMessage.getOutboxStatus())
                .version(availabilityAppointmentOutboxMessage.getVersion())
                .build();
    }

    public AvailabilityAppointmentOutboxMessage outboxEntityToAppointmentOutboxMessage(AppointmentOutboxEntity appointmentOutboxEntity) {
        return AvailabilityAppointmentOutboxMessage.builder()
                .id(appointmentOutboxEntity.getId())
                .payload(appointmentOutboxEntity.getPayload())
                .createdAt(appointmentOutboxEntity.getCreatedAt())
                .outboxStatus(appointmentOutboxEntity.getOutboxStatus())
                .version(appointmentOutboxEntity.getVersion())
                .build();
    }
}
