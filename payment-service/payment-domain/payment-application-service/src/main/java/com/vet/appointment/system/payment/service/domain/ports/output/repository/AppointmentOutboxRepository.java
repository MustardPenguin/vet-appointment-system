package com.vet.appointment.system.payment.service.domain.ports.output.repository;

import com.vet.appointment.system.payment.service.domain.dto.message.outbox.PaymentAppointmentOutboxMessage;

public interface AppointmentOutboxRepository {

    PaymentAppointmentOutboxMessage save(PaymentAppointmentOutboxMessage paymentAppointmentOutboxMessage);
}
