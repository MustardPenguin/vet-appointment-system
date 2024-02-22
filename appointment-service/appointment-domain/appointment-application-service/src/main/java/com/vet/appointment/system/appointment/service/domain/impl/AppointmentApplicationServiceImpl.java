package com.vet.appointment.system.appointment.service.domain.impl;

import com.vet.appointment.system.appointment.service.domain.CreateAppointmentCommandHandler;
import com.vet.appointment.system.appointment.service.domain.GetAppointmentQueryHandler;
import com.vet.appointment.system.appointment.service.domain.dto.create.CreateAppointmentResponse;
import com.vet.appointment.system.appointment.service.domain.dto.create.CreateAppointmentCommand;
import com.vet.appointment.system.appointment.service.domain.dto.get.GetAppointmentQuery;
import com.vet.appointment.system.appointment.service.domain.dto.get.GetAppointmentResponse;
import com.vet.appointment.system.appointment.service.domain.dto.message.AccountModel;
import com.vet.appointment.system.appointment.service.domain.dto.message.PetModel;
import com.vet.appointment.system.appointment.service.domain.event.AppointmentCreatedEvent;
import com.vet.appointment.system.appointment.service.domain.exception.AppointmentDomainException;
import com.vet.appointment.system.appointment.service.domain.ports.input.AppointmentApplicationService;
import com.vet.appointment.system.appointment.service.domain.ports.output.repository.AccountRepository;
import com.vet.appointment.system.appointment.service.domain.ports.output.repository.PetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class AppointmentApplicationServiceImpl implements AppointmentApplicationService {

    private final CreateAppointmentCommandHandler createAppointmentCommandHandler;
    private final GetAppointmentQueryHandler getAppointmentQueryHandler;

    public AppointmentApplicationServiceImpl(CreateAppointmentCommandHandler createAppointmentCommandHandler,
                                             GetAppointmentQueryHandler getAppointmentQueryHandler) {
        this.createAppointmentCommandHandler = createAppointmentCommandHandler;
        this.getAppointmentQueryHandler = getAppointmentQueryHandler;
    }

    @Override
    public CreateAppointmentResponse createAppointment(CreateAppointmentCommand createAppointmentCommand) {
        log.info("Creating appointment at the service layer for owner id {}", createAppointmentCommand.getOwnerId());
        return createAppointmentCommandHandler.createAppointmentFromCommand(createAppointmentCommand);
    }

    @Override
    public GetAppointmentResponse getAppointmentById(UUID appointmentId) {
        log.info("Getting appointment by id at the service layer for appointment id {}", appointmentId);
        return getAppointmentQueryHandler.getAppointmentFromQuery(appointmentId);
    }
}
