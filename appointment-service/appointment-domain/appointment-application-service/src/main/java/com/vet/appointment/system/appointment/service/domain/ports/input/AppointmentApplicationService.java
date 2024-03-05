package com.vet.appointment.system.appointment.service.domain.ports.input;

import com.vet.appointment.system.appointment.service.domain.dto.rest.create.CreateAppointmentResponse;
import com.vet.appointment.system.appointment.service.domain.dto.rest.create.CreateAppointmentCommand;
import com.vet.appointment.system.appointment.service.domain.dto.rest.get.GetAppointmentResponse;

import java.util.UUID;

public interface AppointmentApplicationService {

    CreateAppointmentResponse createAppointment(CreateAppointmentCommand createAppointmentCommand);

    GetAppointmentResponse getAppointmentById(UUID appointmentId);
}
