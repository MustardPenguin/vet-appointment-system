package com.vet.appointment.system.appointment.service.domain;

import com.vet.appointment.system.appointment.service.domain.dto.create.CreateAppointmentCommand;
import com.vet.appointment.system.appointment.service.domain.entity.Appointment;
import com.vet.appointment.system.appointment.service.domain.event.AppointmentCreatedEvent;
import com.vet.appointment.system.appointment.service.domain.mapper.AppointmentDataMapper;
import org.springframework.stereotype.Component;

@Component
public class CreateAppointmentCommandHandler {

    private final AppointmentDataMapper appointmentDataMapper;
    private final AppointmentDomainService appointmentDomainService;

    public CreateAppointmentCommandHandler(AppointmentDataMapper appointmentDataMapper,
                                           AppointmentDomainService appointmentDomainService) {
        this.appointmentDataMapper = appointmentDataMapper;
        this.appointmentDomainService = appointmentDomainService;
    }

    public AppointmentCreatedEvent createAppointmentFromCommand(CreateAppointmentCommand createAppointmentCommand) {
        Appointment appointment = appointmentDataMapper.createAppointmentCommandToAppointment(createAppointmentCommand);
        AppointmentCreatedEvent appointmentCreatedEvent = appointmentDomainService.validateAndInitiateAppointment(appointment);

        return appointmentCreatedEvent;
    }
}
