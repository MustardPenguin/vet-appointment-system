package com.vet.appointment.system.appointment.service.dataaccess.outbox.payment.adapter;

import com.vet.appointment.system.appointment.service.dataaccess.outbox.payment.entity.PaymentOutboxEntity;
import com.vet.appointment.system.appointment.service.dataaccess.outbox.payment.mapper.PaymentOutboxDataAccessMapper;
import com.vet.appointment.system.appointment.service.dataaccess.outbox.payment.repository.PaymentOutboxJpaRepository;
import com.vet.appointment.system.appointment.service.domain.dto.outbox.AppointmentPaymentOutboxMessage;
import com.vet.appointment.system.appointment.service.domain.ports.output.repository.outbox.PaymentOutboxRepository;
import com.vet.appointment.system.saga.SagaStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class PaymentOutboxRepositoryImpl implements PaymentOutboxRepository {

    private final PaymentOutboxJpaRepository paymentOutboxJpaRepository;
    private final PaymentOutboxDataAccessMapper paymentOutboxDataAccessMapper;

    public PaymentOutboxRepositoryImpl(PaymentOutboxJpaRepository paymentOutboxJpaRepository,
                                       PaymentOutboxDataAccessMapper paymentOutboxDataAccessMapper) {
        this.paymentOutboxJpaRepository = paymentOutboxJpaRepository;
        this.paymentOutboxDataAccessMapper = paymentOutboxDataAccessMapper;
    }

    @Override
    public AppointmentPaymentOutboxMessage save(AppointmentPaymentOutboxMessage appointmentPaymentOutboxMessage) {
        PaymentOutboxEntity response = paymentOutboxJpaRepository.save(
                paymentOutboxDataAccessMapper.paymentOutboxMessageToOutboxEntity(appointmentPaymentOutboxMessage));
        return paymentOutboxDataAccessMapper.outboxEntityToPaymentOutboxMessage(response);
    }

    @Override
    public AppointmentPaymentOutboxMessage findBySagaIdAndSagaStatus(String sagaType, UUID sagaId, SagaStatus sagaStatus) {
        return paymentOutboxDataAccessMapper.outboxEntityToPaymentOutboxMessage(
                paymentOutboxJpaRepository.findPaymentOutboxEntityBySagaTypeAndSagaIdAndSagaStatus(sagaType, sagaId, sagaStatus));
    }
}
