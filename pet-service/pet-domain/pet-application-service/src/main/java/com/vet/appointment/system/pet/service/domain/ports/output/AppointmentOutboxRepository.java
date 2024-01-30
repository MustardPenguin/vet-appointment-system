package com.vet.appointment.system.pet.service.domain.ports.output;

import com.vet.appointment.system.pet.service.domain.outbox.model.PetAppointmentOutboxMessage;

public interface AppointmentOutboxRepository {

    PetAppointmentOutboxMessage save(PetAppointmentOutboxMessage petAppointmentOutboxMessage);
}
