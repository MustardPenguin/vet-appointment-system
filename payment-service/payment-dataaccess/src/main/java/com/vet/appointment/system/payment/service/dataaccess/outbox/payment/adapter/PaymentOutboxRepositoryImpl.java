package com.vet.appointment.system.payment.service.dataaccess.outbox.payment.adapter;

import com.vet.appointment.system.payment.service.dataaccess.outbox.payment.entity.PaymentOutboxEntity;
import com.vet.appointment.system.payment.service.dataaccess.outbox.payment.mapper.PaymentOutboxDataMapper;
import com.vet.appointment.system.payment.service.dataaccess.outbox.payment.repository.PaymentOutboxJpaRepository;
import com.vet.appointment.system.payment.service.domain.dto.message.outbox.PaymentOutboxMessage;
import com.vet.appointment.system.payment.service.domain.ports.output.repository.PaymentOutboxRepository;
import org.springframework.stereotype.Component;

@Component
public class PaymentOutboxRepositoryImpl implements PaymentOutboxRepository {

    private final PaymentOutboxJpaRepository paymentOutboxJpaRepository;
    private final PaymentOutboxDataMapper paymentOutboxDataMapper;

    public PaymentOutboxRepositoryImpl(PaymentOutboxJpaRepository paymentOutboxJpaRepository,
                                       PaymentOutboxDataMapper paymentOutboxDataMapper) {
        this.paymentOutboxJpaRepository = paymentOutboxJpaRepository;
        this.paymentOutboxDataMapper = paymentOutboxDataMapper;
    }

    @Override
    public PaymentOutboxMessage save(PaymentOutboxMessage paymentOutboxMessage) {
        try {
            PaymentOutboxEntity response =
                    paymentOutboxJpaRepository.save(paymentOutboxDataMapper.paymentOutboxMessageTOutboxEntity(paymentOutboxMessage));
            return paymentOutboxDataMapper.paymentOutboxEntityToOutboxMessage(response);
        } catch (Exception e) {
            return null;
        }
    }
}
