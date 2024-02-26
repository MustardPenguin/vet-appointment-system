package com.vet.appointment.system.account.service.domain.ports.output.repository;

import com.vet.appointment.system.account.service.domain.dto.outbox.AccountAppointmentOutboxMessage;

public interface AppointmentOutboxRepository {

    AccountAppointmentOutboxMessage save(AccountAppointmentOutboxMessage accountAppointmentOutboxMessage);

    void deleteAccountAppointmentOutboxMessage();
}
