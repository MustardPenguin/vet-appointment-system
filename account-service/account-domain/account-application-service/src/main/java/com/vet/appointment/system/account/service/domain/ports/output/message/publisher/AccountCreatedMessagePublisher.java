package com.vet.appointment.system.account.service.domain.ports.output.message.publisher;

import com.vet.appointment.system.account.service.domain.outbox.model.AccountAppointmentOutboxMessage;
import com.vet.appointment.system.outbox.OutboxStatus;

import java.util.function.BiConsumer;

public interface AccountCreatedMessagePublisher {

    void publish(AccountAppointmentOutboxMessage accountAppointmentOutboxMessage,
                 BiConsumer<AccountAppointmentOutboxMessage, OutboxStatus> outboxCallback);
}
