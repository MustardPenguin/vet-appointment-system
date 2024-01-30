package com.vet.appointment.system.appointment.service.domain.ports.output.repository;

import com.vet.appointment.system.appointment.service.domain.entity.Appointment;

public interface AppointmentRepository {

    void save(Appointment appointment);
}
