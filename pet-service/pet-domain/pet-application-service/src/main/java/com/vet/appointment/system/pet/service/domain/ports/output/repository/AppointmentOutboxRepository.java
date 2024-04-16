package com.vet.appointment.system.pet.service.domain.ports.output.repository;

import com.vet.appointment.system.pet.service.domain.dto.outbox.PetAppointmentOutboxMessage;

import java.util.Optional;
import java.util.UUID;

public interface AppointmentOutboxRepository {

    PetAppointmentOutboxMessage save(PetAppointmentOutboxMessage petAppointmentOutboxMessage);

    void deletePetAppointmentOutboxMessage();

    Optional<PetAppointmentOutboxMessage> getPetAppointmentOutboxMessage(UUID id);
}
