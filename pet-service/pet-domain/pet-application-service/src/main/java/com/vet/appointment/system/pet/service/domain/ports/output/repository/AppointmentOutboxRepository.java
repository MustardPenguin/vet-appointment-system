package com.vet.appointment.system.pet.service.domain.ports.output.repository;

import com.vet.appointment.system.pet.service.domain.dto.outbox.PetAppointmentOutboxMessage;

public interface AppointmentOutboxRepository {

    PetAppointmentOutboxMessage save(PetAppointmentOutboxMessage petAppointmentOutboxMessage);

    void deletePetAppointmentOutboxMessage();

}
