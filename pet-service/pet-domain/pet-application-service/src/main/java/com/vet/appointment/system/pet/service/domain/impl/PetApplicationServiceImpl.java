package com.vet.appointment.system.pet.service.domain.impl;

import com.vet.appointment.system.outbox.OutboxStatus;
import com.vet.appointment.system.pet.service.domain.CreatePetCommandHandler;
import com.vet.appointment.system.pet.service.domain.PetDomainService;
import com.vet.appointment.system.pet.service.domain.dto.create.CreatePetCommand;
import com.vet.appointment.system.pet.service.domain.dto.create.CreatePetResponse;
import com.vet.appointment.system.pet.service.domain.entity.Pet;
import com.vet.appointment.system.pet.service.domain.event.PetCreatedEvent;
import com.vet.appointment.system.pet.service.domain.mapper.PetDataMapper;
import com.vet.appointment.system.pet.service.domain.outbox.scheduler.AppointmentOutboxHelper;
import com.vet.appointment.system.pet.service.domain.ports.input.PetApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PetApplicationServiceImpl implements PetApplicationService {

    private final CreatePetCommandHandler createPetCommandHandler;
    private final AppointmentOutboxHelper appointmentOutboxHelper;
    private final PetDataMapper petDataMapper;

    public PetApplicationServiceImpl(CreatePetCommandHandler createPetCommandHandler,
                                     AppointmentOutboxHelper appointmentOutboxHelper,
                                     PetDataMapper petDataMapper) {
        this.createPetCommandHandler = createPetCommandHandler;
        this.appointmentOutboxHelper = appointmentOutboxHelper;
        this.petDataMapper = petDataMapper;
    }

    @Override
    public CreatePetResponse createPet(CreatePetCommand createPetCommand) {
        log.info("Creating pet for user id {} at service layer", createPetCommand.getOwnerId());

        PetCreatedEvent petCreatedEvent = createPetCommandHandler.createPetFromCommand(createPetCommand);
        appointmentOutboxHelper.saveAppointmentOutboxMessage(
                petDataMapper.petCreatedEventToPetAppointmentEventPayload(petCreatedEvent),
                OutboxStatus.STARTED);

        return new CreatePetResponse("Successfully created pet!", 201);
    }
}
