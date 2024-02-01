package com.vet.appointment.system.appointment.service.domain.mapper;

import com.vet.appointment.system.appointment.service.domain.dto.create.CreateAppointmentCommand;
import com.vet.appointment.system.appointment.service.domain.entity.Appointment;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AppointmentDataMapper {

    public Appointment createAppointmentCommandToAppointment(CreateAppointmentCommand createAppointmentCommand) {
        return Appointment.builder()
                .id(UUID.randomUUID())
                .appointmentStartDateTime(createAppointmentCommand.getAppointmentStartDateTime())
                .appointmentEndDateTime(createAppointmentCommand.getAppointmentEndDateTime())
                .ownerId(createAppointmentCommand.getOwnerId())
                .petId(createAppointmentCommand.getPetId())
                .description(createAppointmentCommand.getDescription())
                .build();
    }
}
