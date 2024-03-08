package com.vet.appointment.system.appointment.service.domain.mapper;

import com.vet.appointment.system.appointment.service.domain.dto.rest.create.CreateAppointmentCommand;
import com.vet.appointment.system.appointment.service.domain.dto.rest.get.GetAppointmentResponse;
import com.vet.appointment.system.appointment.service.domain.entity.Appointment;
import com.vet.appointment.system.appointment.service.domain.event.AppointmentAvailableEvent;
import com.vet.appointment.system.appointment.service.domain.event.AppointmentCreatedEvent;
import com.vet.appointment.system.appointment.service.domain.event.AppointmentEvent;
import com.vet.appointment.system.domain.valueobject.AppointmentStatus;
import com.vet.appointment.system.domain.valueobject.PaymentStatus;
import com.vet.appointment.system.messaging.event.AppointmentAvailabilityEventPayload;
import com.vet.appointment.system.messaging.event.AppointmentPaymentEventPayload;
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
                .appointmentStatus(AppointmentStatus.REQUESTING)
                .paymentStatus(PaymentStatus.PENDING)
                .errorMessages("")
                .build();
    }

    public AppointmentAvailabilityEventPayload appointmentEventToEventPayload(AppointmentEvent appointmentEvent) {
        Appointment appointment = appointmentEvent.getEntity();
        return AppointmentAvailabilityEventPayload.builder()
                .id(appointment.getId().getValue())
                .createdAt(appointmentEvent.getCreatedAt())
                .appointmentStatus(appointment.getAppointmentStatus())
                .appointmentStartDateTime(appointment.getAppointmentStartDateTime())
                .appointmentEndDateTime(appointment.getAppointmentEndDateTime())
                .build();
    }

    public GetAppointmentResponse appointmentToGetAppointmentResponse(Appointment appointment) {
        return GetAppointmentResponse.builder()
                .id(appointment.getId().getValue())
                .appointmentStatus(appointment.getAppointmentStatus())
                .paymentStatus(appointment.getPaymentStatus())
                .appointmentStartDateTime(appointment.getAppointmentStartDateTime())
                .appointmentEndDateTime(appointment.getAppointmentEndDateTime())
                .description(appointment.getDescription())
                .ownerId(appointment.getOwnerId())
                .petId(appointment.getPetId())
                .errorMessages(appointment.getErrorMessages())
                .availabilityId(appointment.getAvailabilityId())
                .paymentId(appointment.getPaymentId())
                .cost(appointment.getCost())
                .message("Successfully fetched appointment")
                .statusCode(200)
                .build();
    }

    public AppointmentPaymentEventPayload appointmentToPaymentEventPayload(AppointmentAvailableEvent appointmentEvent) {
        Appointment appointment = appointmentEvent.getEntity();
        return AppointmentPaymentEventPayload.builder()
                .appointmentId(appointment.getId().getValue())
                .accountId(appointment.getOwnerId())
                .createdAt(appointmentEvent.getCreatedAt())
                .cost(appointment.getCost())
                .reason("Appointment deposit for appointment id: " + appointment.getId().getValue() + " and account id: " + appointment.getOwnerId())
                .build();
    }
}