package com.vet.appointment.system.appointment.service.domain;

import com.vet.appointment.system.appointment.service.domain.entity.Appointment;
import com.vet.appointment.system.appointment.service.domain.event.AppointmentAvailableEvent;
import com.vet.appointment.system.appointment.service.domain.event.AppointmentCreatedEvent;

public interface AppointmentDomainService {

    AppointmentCreatedEvent validateAndInitiateAppointment(Appointment appointment);

    AppointmentAvailableEvent initiateAppointmentAvailability(Appointment appointment);

    void initiateAppointmentUnavailable(Appointment appointment, String errorMessages);
}
