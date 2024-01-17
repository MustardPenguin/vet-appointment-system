package com.vet.appointment.system.appointment.service.domain.ports.input;

import com.vet.appointment.system.appointment.service.domain.dto.create.CreateAppointmentCommand;

public interface AppointmentApplicationService {

    void createAppointment(CreateAppointmentCommand createAppointmentCommand);
}
