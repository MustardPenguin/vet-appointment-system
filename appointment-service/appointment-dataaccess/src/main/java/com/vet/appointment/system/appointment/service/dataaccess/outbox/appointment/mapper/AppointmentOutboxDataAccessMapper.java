package com.vet.appointment.system.appointment.service.dataaccess.outbox.appointment.mapper;

import com.vet.appointment.system.appointment.service.domain.dto.outbox.AppointmentOutboxMessage;
import com.vet.appointment.system.dataaccess.outbox.appointment.entity.AppointmentOutboxEntity;
import org.springframework.stereotype.Component;

@Component
public class AppointmentOutboxDataAccessMapper {

    public AppointmentOutboxEntity appointmentOutboxMessageToOutboxEntity(AppointmentOutboxMessage appointmentOutboxMessage) {
        return AppointmentOutboxEntity.builder()
                .id(appointmentOutboxMessage.getId())
                .payload(appointmentOutboxMessage.getPayload())
                .createdAt(appointmentOutboxMessage.getCreatedAt())
                .version(appointmentOutboxMessage.getVersion())
                .build();
    }

    public AppointmentOutboxMessage appointmentOutboxEntityToOutboxMessage(AppointmentOutboxEntity appointmentOutboxEntity) {
        return AppointmentOutboxMessage.builder()
                .id(appointmentOutboxEntity.getId())
                .payload(appointmentOutboxEntity.getPayload())
                .createdAt(appointmentOutboxEntity.getCreatedAt())
                .version(appointmentOutboxEntity.getVersion())
                .build();
    }
}
