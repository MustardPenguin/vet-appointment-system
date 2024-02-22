package com.vet.appointment.system.appointment.service.domain.ports.input;

import com.vet.appointment.system.appointment.service.domain.dto.create.CreateAppointmentResponse;
import com.vet.appointment.system.appointment.service.domain.dto.create.CreateAppointmentCommand;
import com.vet.appointment.system.appointment.service.domain.dto.get.GetAppointmentQuery;
import com.vet.appointment.system.appointment.service.domain.dto.get.GetAppointmentResponse;

import java.util.UUID;

public interface AppointmentApplicationService {

    CreateAppointmentResponse createAppointment(CreateAppointmentCommand createAppointmentCommand);

    GetAppointmentResponse getAppointmentById(UUID appointmentId);
}
