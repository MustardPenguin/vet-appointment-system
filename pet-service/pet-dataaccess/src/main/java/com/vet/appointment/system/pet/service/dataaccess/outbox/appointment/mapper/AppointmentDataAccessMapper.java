package com.vet.appointment.system.pet.service.dataaccess.outbox.appointment.mapper;

import com.vet.appointment.system.dataaccess.outbox.appointment.entity.AppointmentOutboxEntity;
import com.vet.appointment.system.pet.service.domain.dto.outbox.PetAppointmentOutboxMessage;
import org.springframework.stereotype.Component;

@Component
public class AppointmentDataAccessMapper {

    public AppointmentOutboxEntity petAppointmentOutboxMessageToOutboxEntity(PetAppointmentOutboxMessage petAppointmentOutboxMessage) {
        return AppointmentOutboxEntity.builder()
                .id(petAppointmentOutboxMessage.getId())
                .payload(petAppointmentOutboxMessage.getPayload())
                .createdAt(petAppointmentOutboxMessage.getCreatedAt())
                .build();
    }

    public PetAppointmentOutboxMessage outboxEntityToPetAppointmentOutboxMessage(AppointmentOutboxEntity appointmentOutboxEntity) {
        return PetAppointmentOutboxMessage.builder()
                .id(appointmentOutboxEntity.getId())
                .payload(appointmentOutboxEntity.getPayload())
                .createdAt(appointmentOutboxEntity.getCreatedAt())
                .version(appointmentOutboxEntity.getVersion())
                .build();
    }
}
