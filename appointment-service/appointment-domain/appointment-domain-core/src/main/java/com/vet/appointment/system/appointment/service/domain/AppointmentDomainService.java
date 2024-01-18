package com.vet.appointment.system.appointment.service.domain;

import com.vet.appointment.system.appointment.service.domain.entity.Appointment;
import com.vet.appointment.system.appointment.service.domain.event.AppointmentCreatedEvent;

public interface AppointmentDomainService {

    AppointmentCreatedEvent validateAndInitiateAppointment(Appointment appointment);
}
