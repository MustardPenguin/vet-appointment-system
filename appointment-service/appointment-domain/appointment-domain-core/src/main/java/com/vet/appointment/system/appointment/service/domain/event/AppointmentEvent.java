package com.vet.appointment.system.appointment.service.domain.event;

import com.vet.appointment.system.appointment.service.domain.entity.Appointment;
import com.vet.appointment.system.domain.event.DomainEvent;

import java.time.ZonedDateTime;

public class AppointmentEvent extends DomainEvent<Appointment> {
    public AppointmentEvent(Appointment appointment, ZonedDateTime createdAt) {
        super(appointment, createdAt);
    }
}
