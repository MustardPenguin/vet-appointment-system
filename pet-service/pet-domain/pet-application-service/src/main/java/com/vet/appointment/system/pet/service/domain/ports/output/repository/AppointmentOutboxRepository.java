package com.vet.appointment.system.pet.service.domain.ports.output.repository;

import com.vet.appointment.system.outbox.OutboxStatus;
import com.vet.appointment.system.pet.service.domain.dto.outbox.PetAppointmentOutboxMessage;

import java.util.List;
import java.util.Optional;

public interface AppointmentOutboxRepository {

    PetAppointmentOutboxMessage save(PetAppointmentOutboxMessage petAppointmentOutboxMessage);

    Optional<List<PetAppointmentOutboxMessage>> findByOutboxStatus(OutboxStatus outboxStatus);

    void deleteAppointmentOutboxEntitiesByOutboxStatus(OutboxStatus outboxStatus);

}
