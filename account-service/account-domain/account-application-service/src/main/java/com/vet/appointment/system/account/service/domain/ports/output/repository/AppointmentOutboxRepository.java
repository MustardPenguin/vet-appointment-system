package com.vet.appointment.system.account.service.domain.ports.output.repository;

import com.vet.appointment.system.account.service.domain.outbox.model.AccountAppointmentOutboxMessage;

public interface AppointmentOutboxRepository {

    AccountAppointmentOutboxMessage save(AccountAppointmentOutboxMessage accountAppointmentOutboxMessage);
}
