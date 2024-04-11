package com.vet.appointment.system.appointment.service.domain.event;

import com.vet.appointment.system.appointment.service.domain.entity.Appointment;

import java.time.ZonedDateTime;

public class AppointmentConfirmedEvent extends AppointmentEvent{
    public AppointmentConfirmedEvent(Appointment appointment, ZonedDateTime createdAt) {
        super(appointment, createdAt);
    }
}
