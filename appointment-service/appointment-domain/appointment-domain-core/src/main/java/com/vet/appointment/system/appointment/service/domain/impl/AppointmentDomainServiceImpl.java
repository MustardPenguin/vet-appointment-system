package com.vet.appointment.system.appointment.service.domain.impl;

import com.vet.appointment.system.appointment.service.domain.AppointmentDomainService;
import com.vet.appointment.system.appointment.service.domain.entity.Appointment;
import com.vet.appointment.system.appointment.service.domain.event.AppointmentCreatedEvent;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.vet.appointment.system.domain.DomainConstants.UTC;

public class AppointmentDomainServiceImpl implements AppointmentDomainService {

    @Override
    public AppointmentCreatedEvent validateAndInitiateAppointment(Appointment appointment) {


        return new AppointmentCreatedEvent(appointment, ZonedDateTime.now(ZoneId.of(UTC)));
    }
}
