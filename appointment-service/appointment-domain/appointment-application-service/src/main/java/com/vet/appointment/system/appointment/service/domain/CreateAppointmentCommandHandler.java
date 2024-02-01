package com.vet.appointment.system.appointment.service.domain;

import com.vet.appointment.system.appointment.service.domain.dto.create.CreateAppointmentCommand;
import com.vet.appointment.system.appointment.service.domain.entity.Appointment;
import com.vet.appointment.system.appointment.service.domain.event.AppointmentCreatedEvent;
import com.vet.appointment.system.appointment.service.domain.exception.AppointmentDomainException;
import com.vet.appointment.system.appointment.service.domain.mapper.AppointmentDataMapper;
import com.vet.appointment.system.appointment.service.domain.ports.output.repository.AppointmentRepository;
import com.vet.appointment.system.appointment.service.domain.ports.output.repository.PetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CreateAppointmentCommandHandler {

    private final AppointmentDataMapper appointmentDataMapper;
    private final AppointmentDomainService appointmentDomainService;
    private final AppointmentRepository appointmentRepository;
    private final PetRepository petRepository;

    public CreateAppointmentCommandHandler(AppointmentDataMapper appointmentDataMapper,
                                           AppointmentDomainService appointmentDomainService,
                                           AppointmentRepository appointmentRepository,
                                           PetRepository petRepository) {
        this.appointmentDataMapper = appointmentDataMapper;
        this.appointmentDomainService = appointmentDomainService;
        this.appointmentRepository = appointmentRepository;
        this.petRepository = petRepository;
    }

    public AppointmentCreatedEvent createAppointmentFromCommand(CreateAppointmentCommand createAppointmentCommand) {
        Appointment appointment = appointmentDataMapper.createAppointmentCommandToAppointment(createAppointmentCommand);

        AppointmentCreatedEvent appointmentCreatedEvent = appointmentDomainService.validateAndInitiateAppointment(appointment);
        Appointment response = appointmentRepository.save(appointment);
        if(response == null) {
            log.error("Failed to save appointment for owner id: {}", appointment.getOwnerId());
            throw new AppointmentDomainException("Failed to save appointment for owner id: " + appointment.getOwnerId());
        }
        log.info("Successfully saved appointment with id: {} for owner id: {}", response.getId().getValue(), response.getOwnerId());

        return appointmentCreatedEvent;
    }
}
