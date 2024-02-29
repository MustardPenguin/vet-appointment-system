package com.vet.appointment.system.appointment.service.dataaccess.outbox.payment.repository;

import com.vet.appointment.system.appointment.service.dataaccess.outbox.payment.entity.PaymentOutboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentOutboxJpaRepository extends JpaRepository<PaymentOutboxEntity, UUID> {
}
