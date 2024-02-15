package com.vet.appointment.system.availability.service.domain.ports.input.message.listener;

import com.vet.appointment.system.availability.service.domain.entity.Appointment;

public interface AppointmentAvailabilityMessageListener {

    void checkAvailability(Appointment appointment);
}
