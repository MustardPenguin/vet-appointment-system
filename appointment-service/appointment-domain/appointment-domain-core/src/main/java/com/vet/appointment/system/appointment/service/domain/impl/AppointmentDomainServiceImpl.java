package com.vet.appointment.system.appointment.service.domain.impl;

import com.vet.appointment.system.appointment.service.domain.AppointmentDomainService;
import com.vet.appointment.system.appointment.service.domain.entity.Appointment;
import com.vet.appointment.system.appointment.service.domain.event.AppointmentAvailableEvent;
import com.vet.appointment.system.appointment.service.domain.event.AppointmentCreatedEvent;
import com.vet.appointment.system.appointment.service.domain.exception.AppointmentDomainException;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.vet.appointment.system.domain.DomainConstants.UTC;

public class AppointmentDomainServiceImpl implements AppointmentDomainService {

    @Override
    public AppointmentCreatedEvent validateAndInitiateAppointment(Appointment appointment) {
        if(appointment.getAppointmentStartDateTime().isAfter(appointment.getAppointmentEndDateTime())) {
            throw new AppointmentDomainException("Appointment start date time is after appointment end date time!");
        }
        if(appointment.getAppointmentStartDateTime().getDayOfMonth() != appointment.getAppointmentEndDateTime().getDayOfMonth()) {
            throw new AppointmentDomainException("Appointment start date time and appointment end date time are not on the same day!");
        }

        return new AppointmentCreatedEvent(appointment, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public AppointmentAvailableEvent initiateAppointmentAvailability(Appointment appointment) {
        appointment.initAvailability();
        return new AppointmentAvailableEvent(appointment, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public void initiateAppointmentUnavailable(Appointment appointment, String errorMessages) {
        appointment.initUnavailability(errorMessages);
    }
}
