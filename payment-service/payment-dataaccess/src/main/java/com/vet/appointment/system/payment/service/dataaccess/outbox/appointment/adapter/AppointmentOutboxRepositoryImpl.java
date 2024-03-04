package com.vet.appointment.system.payment.service.dataaccess.outbox.appointment.adapter;

import com.vet.appointment.system.payment.service.dataaccess.outbox.appointment.entity.AppointmentOutboxEntity;
import com.vet.appointment.system.payment.service.dataaccess.outbox.appointment.mapper.AppointmentDataAccessMapper;
import com.vet.appointment.system.payment.service.dataaccess.outbox.appointment.repository.AppointmentOutboxJpaRepository;
import com.vet.appointment.system.payment.service.domain.dto.message.outbox.PaymentAppointmentOutboxMessage;
import com.vet.appointment.system.payment.service.domain.ports.output.repository.AppointmentOutboxRepository;
import org.springframework.stereotype.Component;

@Component
public class AppointmentOutboxRepositoryImpl implements AppointmentOutboxRepository {

    private final AppointmentOutboxJpaRepository appointmentOutboxJpaRepository;
    private final AppointmentDataAccessMapper appointmentDataAccessMapper;

    public AppointmentOutboxRepositoryImpl(AppointmentOutboxJpaRepository appointmentOutboxJpaRepository,
                                           AppointmentDataAccessMapper appointmentDataAccessMapper) {
        this.appointmentOutboxJpaRepository = appointmentOutboxJpaRepository;
        this.appointmentDataAccessMapper = appointmentDataAccessMapper;
    }

    @Override
    public PaymentAppointmentOutboxMessage save(PaymentAppointmentOutboxMessage paymentAppointmentOutboxMessage) {
        AppointmentOutboxEntity response = appointmentOutboxJpaRepository
                .save(appointmentDataAccessMapper.appointmentOutboxMessageToEntity(paymentAppointmentOutboxMessage));
        return appointmentDataAccessMapper.appointmentOutboxEntityToMessage(response);
    }
}
