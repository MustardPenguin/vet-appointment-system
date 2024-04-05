package com.vet.appointment.system.appointment.service.domain;

import com.vet.appointment.system.appointment.service.domain.dto.rest.create.CreateAppointmentCommand;
import com.vet.appointment.system.appointment.service.domain.dto.rest.create.CreateAppointmentResponse;
import com.vet.appointment.system.appointment.service.domain.entity.Appointment;
import com.vet.appointment.system.appointment.service.domain.event.AppointmentCreatedEvent;
import com.vet.appointment.system.appointment.service.domain.helper.AppointmentOutboxHelper;
import com.vet.appointment.system.appointment.service.domain.helper.AppointmentServiceDataHelper;
import com.vet.appointment.system.appointment.service.domain.mapper.AppointmentDataMapper;
import com.vet.appointment.system.appointment.service.domain.helper.AvailabilityOutboxHelper;
import com.vet.appointment.system.appointment.service.domain.ports.output.repository.AppointmentRepository;
import com.vet.appointment.system.saga.SagaStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
public class CreateAppointmentCommandHandler {

    private final AppointmentDataMapper appointmentDataMapper;
    private final AppointmentDomainService appointmentDomainService;
    private final AvailabilityOutboxHelper availabilityOutboxHelper;
    private final AppointmentServiceDataHelper appointmentServiceDataHelper;
    private final AppointmentOutboxHelper appointmentOutboxHelper;

    public CreateAppointmentCommandHandler(AppointmentDataMapper appointmentDataMapper,
                                           AppointmentDomainService appointmentDomainService,
                                           AvailabilityOutboxHelper availabilityOutboxHelper,
                                           AppointmentServiceDataHelper appointmentServiceDataHelper,
                                           AppointmentOutboxHelper appointmentOutboxHelper) {
        this.appointmentDataMapper = appointmentDataMapper;
        this.appointmentDomainService = appointmentDomainService;
        this.availabilityOutboxHelper = availabilityOutboxHelper;
        this.appointmentServiceDataHelper = appointmentServiceDataHelper;
        this.appointmentOutboxHelper = appointmentOutboxHelper;
    }

    @Transactional
    public CreateAppointmentResponse createAppointmentFromCommand(CreateAppointmentCommand createAppointmentCommand) {
        appointmentServiceDataHelper.validateAccountAndPet(createAppointmentCommand.getOwnerId(), createAppointmentCommand.getPetId());
        Appointment appointment = appointmentDataMapper.createAppointmentCommandToAppointment(createAppointmentCommand);

        AppointmentCreatedEvent appointmentCreatedEvent =
                appointmentDomainService.validateAndInitiateAppointment(appointment);

        Appointment response = appointmentServiceDataHelper.saveAppointmentEntity(appointment);
        availabilityOutboxHelper.saveAvailabilityOutboxMessage(
                appointmentDataMapper.appointmentEventToEventPayload(appointmentCreatedEvent),
                SagaStatus.PROCESSING,
                UUID.randomUUID());
        log.info("Successfully saved appointment with id: {} for owner id: {}",
                response.getId().getValue(), response.getOwnerId());

        appointmentOutboxHelper.saveAppointmentOutboxMessage(appointmentCreatedEvent);

        return new CreateAppointmentResponse(
                "Successfully requested appointment! Use the appointmentId to track your appointment status",
                200,
                appointmentCreatedEvent.getEntity().getId().getValue(),
                appointmentCreatedEvent.getCreatedAt());
    }
}
