package com.vet.appointment.system.outbox;

public interface OutboxScheduler {

    void processOutboxMessage();
}
