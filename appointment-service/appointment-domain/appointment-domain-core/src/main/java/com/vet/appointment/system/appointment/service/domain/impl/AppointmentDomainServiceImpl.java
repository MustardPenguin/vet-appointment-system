package com.vet.appointment.system.appointment.service.domain.impl;

import com.vet.appointment.system.appointment.service.domain.AppointmentDomainService;
import com.vet.appointment.system.appointment.service.domain.entity.Appointment;
import com.vet.appointment.system.appointment.service.domain.event.AppointmentAvailableEvent;
import com.vet.appointment.system.appointment.service.domain.event.AppointmentCancelledEvent;
import com.vet.appointment.system.appointment.service.domain.event.AppointmentCreatedEvent;
import com.vet.appointment.system.appointment.service.domain.exception.AppointmentDomainException;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.vet.appointment.system.domain.DomainConstants.UTC;

public class AppointmentDomainServiceImpl implements AppointmentDomainService {

    @Override
    public AppointmentCreatedEvent validateAndInitiateAppointment(Appointment appointment) {
        appointment.initAppointment();
        return new AppointmentCreatedEvent(appointment, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public AppointmentAvailableEvent initiateAppointmentAvailability(Appointment appointment) {
        appointment.initAvailability();
        return new AppointmentAvailableEvent(appointment, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public AppointmentCancelledEvent initiateAppointmentUnavailable(Appointment appointment, String errorMessages) {
        appointment.initUnavailability(errorMessages);
        return new AppointmentCancelledEvent(appointment, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public AppointmentCancelledEvent initiateAppointmentCancelled(Appointment appointment, String errorMessages) {
        appointment.initCancelling(errorMessages);
        return new AppointmentCancelledEvent(appointment, ZonedDateTime.now(ZoneId.of(UTC)));
    }
}
