package com.vet.appointment.system.appointment.service.domain;

import com.vet.appointment.system.appointment.service.domain.entity.Appointment;
import com.vet.appointment.system.appointment.service.domain.event.AppointmentAvailableEvent;
import com.vet.appointment.system.appointment.service.domain.event.AppointmentCancelledEvent;
import com.vet.appointment.system.appointment.service.domain.event.AppointmentCreatedEvent;

public interface AppointmentDomainService {

    AppointmentCreatedEvent validateAndInitiateAppointment(Appointment appointment);

    AppointmentAvailableEvent initiateAppointmentAvailability(Appointment appointment);

    AppointmentCancelledEvent initiateAppointmentUnavailable(Appointment appointment, String errorMessages);

    AppointmentCancelledEvent initiateAppointmentCancelled(Appointment appointment, String errorMessages);
}
