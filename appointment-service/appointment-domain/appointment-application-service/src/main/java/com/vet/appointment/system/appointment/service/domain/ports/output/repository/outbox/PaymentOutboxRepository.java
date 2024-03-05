package com.vet.appointment.system.appointment.service.domain.ports.output.repository.outbox;

import com.vet.appointment.system.appointment.service.domain.dto.outbox.AppointmentPaymentOutboxMessage;
import com.vet.appointment.system.saga.SagaStatus;

import java.util.Optional;
import java.util.UUID;

public interface PaymentOutboxRepository {

    AppointmentPaymentOutboxMessage save(AppointmentPaymentOutboxMessage appointmentPaymentOutboxMessage);

    AppointmentPaymentOutboxMessage findBySagaIdAndSagaStatus(String sagaType, UUID sagaId, SagaStatus sagaStatus);
}
