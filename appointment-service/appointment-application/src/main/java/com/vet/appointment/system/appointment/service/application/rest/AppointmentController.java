package com.vet.appointment.system.appointment.service.application.rest;

import com.vet.appointment.system.appointment.service.domain.dto.create.CreateAppointmentCommand;
import com.vet.appointment.system.appointment.service.domain.ports.input.AppointmentApplicationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppointmentController {

    private final AppointmentApplicationService appointmentApplicationService;

    public AppointmentController(AppointmentApplicationService appointmentApplicationService) {
        this.appointmentApplicationService = appointmentApplicationService;
    }


    @GetMapping("/api/test")
    public String test() {
        return "test";
    }

    @PostMapping("/api/appointment")
    public String createAppointmentRequest(@RequestBody CreateAppointmentCommand createAppointmentCommand) {

        appointmentApplicationService.createAppointment(createAppointmentCommand);

        return "response";
    }
}
