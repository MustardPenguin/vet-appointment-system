package com.vet.appointment.system.appointment.service.domain.saga;

import com.vet.appointment.system.appointment.service.domain.AppointmentDomainService;
import com.vet.appointment.system.appointment.service.domain.dto.message.AvailabilityResponse;
import com.vet.appointment.system.appointment.service.domain.dto.outbox.AppointmentAvailabilityOutboxMessage;
import com.vet.appointment.system.appointment.service.domain.entity.Appointment;
import com.vet.appointment.system.appointment.service.domain.event.AppointmentAvailableEvent;
import com.vet.appointment.system.appointment.service.domain.helper.AppointmentServiceHelper;
import com.vet.appointment.system.appointment.service.domain.helper.AvailabilityOutboxHelper;
import com.vet.appointment.system.domain.valueobject.AppointmentStatus;
import com.vet.appointment.system.saga.SagaStatus;
import com.vet.appointment.system.saga.SagaSteps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class AppointmentAvailabilitySaga implements SagaSteps<AvailabilityResponse> {

    private final AvailabilityOutboxHelper availabilityOutboxHelper;
    private final AppointmentDomainService appointmentDomainService;
    private final AppointmentServiceHelper appointmentServiceHelper;

    public AppointmentAvailabilitySaga(AvailabilityOutboxHelper availabilityOutboxHelper,
                                       AppointmentDomainService appointmentDomainService,
                                       AppointmentServiceHelper appointmentServiceHelper) {
        this.availabilityOutboxHelper = availabilityOutboxHelper;
        this.appointmentDomainService = appointmentDomainService;
        this.appointmentServiceHelper = appointmentServiceHelper;
    }

    @Override
    @Transactional
    public void process(AvailabilityResponse availabilityResponse) {
        AppointmentAvailabilityOutboxMessage appointmentAvailabilityOutboxMessage = getOutboxMessage(availabilityResponse);
        if(appointmentAvailabilityOutboxMessage == null) {
            return;
        }

        Appointment appointment = appointmentServiceHelper.getAppointmentById(availabilityResponse.getAppointmentId());
        appointment.setAvailabilityId(availabilityResponse.getAvailabilityId());
        AppointmentAvailableEvent appointmentAvailableEvent = appointmentDomainService.initiateAppointmentAvailability(appointment);
        Appointment response = appointmentServiceHelper.saveAppointmentEntity(appointmentAvailableEvent.getEntity());
        log.info("Successfully saved appointment with id: {} as status {} with availability id: {}",
                response.getId().getValue(), response.getAppointmentStatus(), response.getAvailabilityId());

        appointmentAvailabilityOutboxMessage.setSagaStatus(SagaStatus.SUCCEEDED);
        availabilityOutboxHelper.save(appointmentAvailabilityOutboxMessage);
    }

    @Override
    @Transactional
    public void rollback(AvailabilityResponse availabilityResponse) {
        AppointmentAvailabilityOutboxMessage appointmentAvailabilityOutboxMessage = getOutboxMessage(availabilityResponse);
        if(appointmentAvailabilityOutboxMessage == null) {
            return;
        }

        Appointment appointment = appointmentServiceHelper.getAppointmentById(availabilityResponse.getAppointmentId());
        appointmentDomainService.initiateAppointmentUnavailable(appointment, availabilityResponse.getErrorMessages());
        Appointment response = appointmentServiceHelper.saveAppointmentEntity(appointment);
        log.info("Successfully saved appointment with id: {} as status {}", response.getId().getValue(), response.getAppointmentStatus());

        appointmentAvailabilityOutboxMessage.setSagaStatus(SagaStatus.COMPENSATED);
        availabilityOutboxHelper.save(appointmentAvailabilityOutboxMessage);
    }

    private AppointmentAvailabilityOutboxMessage getOutboxMessage(AvailabilityResponse availabilityResponse) {
        Optional<AppointmentAvailabilityOutboxMessage> appointmentAvailabilityOutboxMessageOptional
                = availabilityOutboxHelper.findAvailabilityOutboxMessageBySagaIdAndSagaStatus(availabilityResponse.getSagaId(), SagaStatus.PROCESSING);

        if(appointmentAvailabilityOutboxMessageOptional.isEmpty()) {
            log.info("Could not find an outbox message with saga id: {} and status: PROCESSING, message was possibly already processed.", availabilityResponse.getSagaId());
            return null;
        }
        log.info("Outbox message is found with saga id: {} and status: PROCESSING", availabilityResponse.getSagaId());

        return appointmentAvailabilityOutboxMessageOptional.get();
    }
}
