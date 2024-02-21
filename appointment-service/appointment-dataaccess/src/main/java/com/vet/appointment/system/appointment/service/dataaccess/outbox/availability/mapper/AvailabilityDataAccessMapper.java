package com.vet.appointment.system.appointment.service.dataaccess.outbox.availability.mapper;

import com.vet.appointment.system.appointment.service.dataaccess.outbox.availability.entity.AvailabilityOutboxEntity;
import com.vet.appointment.system.appointment.service.domain.dto.outbox.AppointmentAvailabilityOutboxMessage;
import org.springframework.stereotype.Component;

@Component
public class AvailabilityDataAccessMapper {

    public AvailabilityOutboxEntity availabilityOutboxMessageToOutboxEntity(AppointmentAvailabilityOutboxMessage appointmentAvailabilityOutboxMessage) {
        return AvailabilityOutboxEntity.builder()
                .id(appointmentAvailabilityOutboxMessage.getId())
                .payload(appointmentAvailabilityOutboxMessage.getPayload())
                .createdAt(appointmentAvailabilityOutboxMessage.getCreatedAt())
                .outboxStatus(appointmentAvailabilityOutboxMessage.getOutboxStatus())
                .version(appointmentAvailabilityOutboxMessage.getVersion())
                .build();
    }

    public AppointmentAvailabilityOutboxMessage availabilityOutboxEntityToOutboxMessage(AvailabilityOutboxEntity availabilityOutboxEntity) {
        return AppointmentAvailabilityOutboxMessage.builder()
                .id(availabilityOutboxEntity.getId())
                .payload(availabilityOutboxEntity.getPayload())
                .createdAt(availabilityOutboxEntity.getCreatedAt())
                .outboxStatus(availabilityOutboxEntity.getOutboxStatus())
                .version(availabilityOutboxEntity.getVersion())
                .build();
    }
}
