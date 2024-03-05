package com.vet.appointment.system.appointment.service.messaging.mapper;

import com.vet.appointment.system.appointment.service.domain.dto.message.AvailabilityResponse;
import com.vet.appointment.system.appointment.service.domain.dto.message.PaymentResponse;
import com.vet.appointment.system.messaging.event.AvailabilityAppointmentEventPayload;
import com.vet.appointment.system.messaging.event.PaymentAppointmentEventPayload;
import org.springframework.stereotype.Component;

import java.util.UUID;

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
}
