package com.vet.appointment.system.pet.service.domain.ports.output.message.publisher;

import com.vet.appointment.system.outbox.OutboxStatus;
import com.vet.appointment.system.pet.service.domain.outbox.model.PetAppointmentOutboxMessage;

import java.util.function.BiConsumer;

public interface PetCreatedMessagePublisher {

    void publish(PetAppointmentOutboxMessage petAppointmentOutboxMessage,
                 BiConsumer<PetAppointmentOutboxMessage, OutboxStatus> outboxCallback);
}
