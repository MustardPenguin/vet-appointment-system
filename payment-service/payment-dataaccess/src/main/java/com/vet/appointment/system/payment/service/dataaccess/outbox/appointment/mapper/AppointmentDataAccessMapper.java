package com.vet.appointment.system.payment.service.dataaccess.outbox.appointment.mapper;

import com.vet.appointment.system.payment.service.dataaccess.outbox.appointment.entity.AppointmentOutboxEntity;
import com.vet.appointment.system.payment.service.domain.dto.message.outbox.PaymentAppointmentOutboxMessage;
import org.springframework.stereotype.Component;

@Component
public class AppointmentDataAccessMapper {

    public AppointmentOutboxEntity appointmentOutboxMessageToEntity(PaymentAppointmentOutboxMessage paymentAppointmentOutboxMessage) {
        return AppointmentOutboxEntity.builder()
                .id(paymentAppointmentOutboxMessage.getId())
                .sagaType(paymentAppointmentOutboxMessage.getSagaType())
                .sagaId(paymentAppointmentOutboxMessage.getSagaId())
                .createdAt(paymentAppointmentOutboxMessage.getCreatedAt())
                .payload(paymentAppointmentOutboxMessage.getPayload())
                .version(paymentAppointmentOutboxMessage.getVersion())
                .build();
    }

    public PaymentAppointmentOutboxMessage appointmentOutboxEntityToMessage(AppointmentOutboxEntity appointmentOutboxEntity) {
        return PaymentAppointmentOutboxMessage.builder()
                .id(appointmentOutboxEntity.getId())
                .sagaType(appointmentOutboxEntity.getSagaType())
                .sagaId(appointmentOutboxEntity.getSagaId())
                .createdAt(appointmentOutboxEntity.getCreatedAt())
                .payload(appointmentOutboxEntity.getPayload())
                .version(appointmentOutboxEntity.getVersion())
                .build();
    }
}
