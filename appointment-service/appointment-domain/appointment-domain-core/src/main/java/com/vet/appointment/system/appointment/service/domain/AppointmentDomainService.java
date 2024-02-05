package com.vet.appointment.system.appointment.service.domain;

import com.vet.appointment.system.appointment.service.domain.entity.Appointment;
import com.vet.appointment.system.appointment.service.domain.entity.Pet;
import com.vet.appointment.system.appointment.service.domain.event.AppointmentCreatedEvent;

import java.util.List;

public interface AppointmentDomainService {

    AppointmentCreatedEvent validateAndInitiateAppointment(Appointment appointment, Pet pet, List<String> errorMessages);
}
