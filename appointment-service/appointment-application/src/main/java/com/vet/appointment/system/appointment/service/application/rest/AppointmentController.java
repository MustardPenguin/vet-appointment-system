package com.vet.appointment.system.appointment.service.application.rest;

import com.vet.appointment.system.appointment.service.domain.dto.create.CreateAppointmentCommand;
import com.vet.appointment.system.appointment.service.domain.ports.input.AppointmentApplicationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class AppointmentController {

    private final AppointmentApplicationService appointmentApplicationService;

    public AppointmentController(AppointmentApplicationService appointmentApplicationService) {
        this.appointmentApplicationService = appointmentApplicationService;
    }

    @PostMapping("/api/appointment")
    public String createAppointmentRequest(
            @RequestBody @Valid CreateAppointmentCommand createAppointmentCommand,
            @RequestHeader("accountId") UUID accountId) {
        createAppointmentCommand.setOwnerId(accountId);
        appointmentApplicationService.createAppointment(createAppointmentCommand);

        return "response";
    }
}
