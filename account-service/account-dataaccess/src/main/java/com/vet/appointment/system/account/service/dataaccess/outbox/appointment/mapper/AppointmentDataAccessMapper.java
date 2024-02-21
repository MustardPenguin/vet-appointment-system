package com.vet.appointment.system.account.service.dataaccess.outbox.appointment.mapper;

import com.vet.appointment.system.account.service.domain.dto.outbox.AccountAppointmentOutboxMessage;
import com.vet.appointment.system.dataaccess.outbox.appointment.entity.AppointmentOutboxEntity;
import org.springframework.stereotype.Component;

@Component
public class AppointmentDataAccessMapper {

    public AppointmentOutboxEntity accountAppointmentOutboxMessageToOutboxEntity(AccountAppointmentOutboxMessage accountAppointmentOutboxMessage) {
        return AppointmentOutboxEntity.builder()
                .id(accountAppointmentOutboxMessage.getId())
                .createdAt(accountAppointmentOutboxMessage.getCreatedAt())
                .payload(accountAppointmentOutboxMessage.getPayload())
                .outboxStatus(accountAppointmentOutboxMessage.getOutboxStatus())
                .version(accountAppointmentOutboxMessage.getVersion())
                .build();
    }

    public AccountAppointmentOutboxMessage outboxEntityToAccountAppointmentOutboxMessage(AppointmentOutboxEntity appointmentOutboxEntity) {
        return AccountAppointmentOutboxMessage.builder()
                .id(appointmentOutboxEntity.getId())
                .createdAt(appointmentOutboxEntity.getCreatedAt())
                .payload(appointmentOutboxEntity.getPayload())
                .outboxStatus(appointmentOutboxEntity.getOutboxStatus())
                .version(appointmentOutboxEntity.getVersion())
                .build();
    }
}
