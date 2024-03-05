package com.vet.appointment.system.appointment.service.dataaccess.outbox.payment.repository;

import com.vet.appointment.system.appointment.service.dataaccess.outbox.payment.entity.PaymentOutboxEntity;
import com.vet.appointment.system.saga.SagaStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentOutboxJpaRepository extends JpaRepository<PaymentOutboxEntity, UUID> {

    PaymentOutboxEntity findPaymentOutboxEntityBySagaTypeAndSagaIdAndSagaStatus(String sagaType, UUID sagaId, SagaStatus sagaStatus);
}
