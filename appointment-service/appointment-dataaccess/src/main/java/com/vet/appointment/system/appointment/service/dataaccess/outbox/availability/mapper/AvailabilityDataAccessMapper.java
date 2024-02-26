package com.vet.appointment.system.appointment.service.dataaccess.outbox.availability.mapper;

import com.vet.appointment.system.appointment.service.dataaccess.outbox.availability.entity.AvailabilityOutboxEntity;
import com.vet.appointment.system.appointment.service.domain.dto.outbox.AppointmentAvailabilityOutboxMessage;
import org.springframework.stereotype.Component;

@Component
public class AvailabilityDataAccessMapper {

    public AvailabilityOutboxEntity availabilityOutboxMessageToOutboxEntity(AppointmentAvailabilityOutboxMessage appointmentAvailabilityOutboxMessage) {
        return AvailabilityOutboxEntity.builder()
                .id(appointmentAvailabilityOutboxMessage.getId())
                .sagaId(appointmentAvailabilityOutboxMessage.getSagaId())
                .sagaType(appointmentAvailabilityOutboxMessage.getSagaType())
                .payload(appointmentAvailabilityOutboxMessage.getPayload())
                .createdAt(appointmentAvailabilityOutboxMessage.getCreatedAt())
                .sagaStatus(appointmentAvailabilityOutboxMessage.getSagaStatus())
                .version(appointmentAvailabilityOutboxMessage.getVersion())
                .build();
    }

    public AppointmentAvailabilityOutboxMessage availabilityOutboxEntityToOutboxMessage(AvailabilityOutboxEntity availabilityOutboxEntity) {
        return AppointmentAvailabilityOutboxMessage.builder()
                .id(availabilityOutboxEntity.getId())
                .sagaId(availabilityOutboxEntity.getSagaId())
                .sagaType(availabilityOutboxEntity.getSagaType())
                .payload(availabilityOutboxEntity.getPayload())
                .createdAt(availabilityOutboxEntity.getCreatedAt())
                .version(availabilityOutboxEntity.getVersion())
                .sagaStatus(availabilityOutboxEntity.getSagaStatus())
                .build();
    }
}
