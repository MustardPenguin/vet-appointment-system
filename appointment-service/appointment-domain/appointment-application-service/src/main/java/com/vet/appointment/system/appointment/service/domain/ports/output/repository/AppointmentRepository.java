package com.vet.appointment.system.appointment.service.domain.ports.output.repository;

import com.vet.appointment.system.appointment.service.domain.dto.message.AccountModel;
import com.vet.appointment.system.appointment.service.domain.entity.Appointment;
import com.vet.appointment.system.domain.valueobject.AccountId;

import java.util.Optional;

public interface AppointmentRepository {

    Appointment save(Appointment appointment);
}
