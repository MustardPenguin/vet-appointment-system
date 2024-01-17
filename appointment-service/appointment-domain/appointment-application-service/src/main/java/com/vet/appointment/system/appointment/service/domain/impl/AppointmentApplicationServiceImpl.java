package com.vet.appointment.system.appointment.service.domain.impl;

import com.vet.appointment.system.appointment.service.domain.dto.create.CreateAppointmentCommand;
import com.vet.appointment.system.appointment.service.domain.ports.input.AppointmentApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AppointmentApplicationServiceImpl implements AppointmentApplicationService {
    @Override
    public void createAppointment(CreateAppointmentCommand createAppointmentCommand) {
        log.info("Creating appointment at the service layer for owner id {}", createAppointmentCommand.getOwnerId());


    }
}
