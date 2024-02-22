package com.vet.appointment.system.appointment.service.application.rest;

import com.vet.appointment.system.appointment.service.domain.dto.create.CreateAppointmentResponse;
import com.vet.appointment.system.appointment.service.domain.dto.create.CreateAppointmentCommand;
import com.vet.appointment.system.appointment.service.domain.dto.get.GetAppointmentQuery;
import com.vet.appointment.system.appointment.service.domain.dto.get.GetAppointmentResponse;
import com.vet.appointment.system.appointment.service.domain.ports.input.AppointmentApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class AppointmentController {

    private final AppointmentApplicationService appointmentApplicationService;

    public AppointmentController(AppointmentApplicationService appointmentApplicationService) {
        this.appointmentApplicationService = appointmentApplicationService;
    }

    @PostMapping("/api/appointment")
    public ResponseEntity<CreateAppointmentResponse> createAppointmentRequest(
            @RequestBody @Valid CreateAppointmentCommand createAppointmentCommand,
            @RequestHeader("accountId") UUID accountId) {
        createAppointmentCommand.setOwnerId(accountId);
        CreateAppointmentResponse createAppointmentResponse = appointmentApplicationService.createAppointment(createAppointmentCommand);

        return ResponseEntity.ok(createAppointmentResponse);
    }

    @GetMapping("/api/appointment/{appointmentId}")
    public ResponseEntity<GetAppointmentResponse> findAppointmentById(@PathVariable UUID appointmentId) {
        GetAppointmentResponse getAppointmentResponse = appointmentApplicationService.getAppointmentById(appointmentId);
        return ResponseEntity.ok(getAppointmentResponse);
    }
}
