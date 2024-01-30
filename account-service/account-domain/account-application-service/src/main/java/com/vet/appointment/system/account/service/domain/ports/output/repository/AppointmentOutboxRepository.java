package com.vet.appointment.system.account.service.domain.ports.output.repository;

import com.vet.appointment.system.account.service.domain.outbox.model.AccountAppointmentOutboxMessage;
import com.vet.appointment.system.outbox.OutboxStatus;

import java.util.List;
import java.util.Optional;

public interface AppointmentOutboxRepository {

    AccountAppointmentOutboxMessage save(AccountAppointmentOutboxMessage accountAppointmentOutboxMessage);

    Optional<List<AccountAppointmentOutboxMessage>> findByOutboxStatus(OutboxStatus outboxStatus);
}