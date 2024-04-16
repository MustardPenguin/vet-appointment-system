package com.vet.appointment.system.appointment.service.messaging.mapper;

import appointment_created.appointment.appointment_outbox.Value;
import com.vet.appointment.system.appointment.service.domain.dto.message.AvailabilityResponse;
import com.vet.appointment.system.appointment.service.domain.dto.message.PaymentResponse;
import com.vet.appointment.system.appointment.service.domain.dto.message.PetModel;
import com.vet.appointment.system.appointment.service.domain.dto.outbox.AppointmentCreatedEventPayload;
import com.vet.appointment.system.appointment.service.domain.entity.Appointment;
import com.vet.appointment.system.domain.valueobject.AppointmentStatus;
import com.vet.appointment.system.domain.valueobject.PaymentStatus;
import com.vet.appointment.system.messaging.event.AvailabilityAppointmentEventPayload;
import com.vet.appointment.system.messaging.event.PaymentAppointmentEventPayload;
import com.vet.appointment.system.messaging.event.PetCreatedEventPayload;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import static com.vet.appointment.system.domain.DomainConstants.UTC;

@Component
public class AppointmentMessagingDataMapper {

    public AvailabilityResponse availabilityAppointmentEventPayloadToAvailabilityResponse(AvailabilityAppointmentEventPayload availabilityAppointmentEventPayload,
                                                                                          UUID sagaId) {
        return AvailabilityResponse.builder()
                .appointmentId(availabilityAppointmentEventPayload.getAppointmentId())
                .availabilityId(UUID.fromString(availabilityAppointmentEventPayload.getAvailabilityId()))
                .sagaId(sagaId)
                .createdAt(availabilityAppointmentEventPayload.getCreatedAt())
                .appointmentStatus(availabilityAppointmentEventPayload.getAppointmentStatus())
                .errorMessages(availabilityAppointmentEventPayload.getErrorMessages())
                .build();
    }

    public PaymentResponse paymentAppointmentEventPayloadToPaymentResponse(PaymentAppointmentEventPayload paymentAppointmentEventPayload,
                                                                           UUID sagaId) {
        return PaymentResponse.builder()
                .appointmentId(paymentAppointmentEventPayload.getAppointmentId())
                .paymentId(paymentAppointmentEventPayload.getPaymentId())
                .paymentStatus(paymentAppointmentEventPayload.getPaymentStatus())
                .createdAt(paymentAppointmentEventPayload.getCreatedAt())
                .errorMessages(paymentAppointmentEventPayload.getErrorMessages())
                .sagaId(sagaId)
                .build();
    }

    public Appointment appointmentCreatedEventPayloadToAppointment(AppointmentCreatedEventPayload appointmentCreatedEventPayload) {
        UUID paymentId = appointmentCreatedEventPayload.getPaymentId() == null ? null : appointmentCreatedEventPayload.getPaymentId();
        UUID availabilityId = appointmentCreatedEventPayload.getAvailabilityId() == null ? null : appointmentCreatedEventPayload.getAvailabilityId();
        BigDecimal cost = appointmentCreatedEventPayload.getCost() == null ? null : appointmentCreatedEventPayload.getCost();
        return Appointment.builder()
                .id(appointmentCreatedEventPayload.getId())
                .ownerId(appointmentCreatedEventPayload.getOwnerId())
                .petId(appointmentCreatedEventPayload.getPetId())
                .paymentId(paymentId)
                .availabilityId(availabilityId)
                .errorMessages(appointmentCreatedEventPayload.getErrorMessages())
                .paymentStatus(appointmentCreatedEventPayload.getPaymentStatus())
                .appointmentStatus(appointmentCreatedEventPayload.getAppointmentStatus())
                .description(appointmentCreatedEventPayload.getDescription())
                .appointmentStartDateTime(appointmentCreatedEventPayload.getAppointmentStartDateTime())
                .appointmentEndDateTime(appointmentCreatedEventPayload.getAppointmentEndDateTime())
                .cost(cost)
                .build();
    }

    public PetModel petEventToPetModel(PetCreatedEventPayload petCreatedEventPayload) {
        return PetModel.builder()
                .id(UUID.fromString(petCreatedEventPayload.getId()))
                .ownerId(UUID.fromString(petCreatedEventPayload.getOwnerId()))
                .name(petCreatedEventPayload.getName())
                .species(petCreatedEventPayload.getSpecies())
                .birthDate(petCreatedEventPayload.getBirthDate())
                .build();
    }
}
