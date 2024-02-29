package com.vet.appointment.system.appointment.service.domain.ports.output.repository.outbox;

import com.vet.appointment.system.appointment.service.domain.dto.outbox.AppointmentPaymentOutboxMessage;

public interface PaymentOutboxRepository {

    AppointmentPaymentOutboxMessage save(AppointmentPaymentOutboxMessage appointmentPaymentOutboxMessage);
}
