package com.vet.appointment.system.pet.service.domain;

import com.vet.appointment.system.pet.service.domain.dto.create.CreatePetCommand;
import com.vet.appointment.system.pet.service.domain.dto.create.CreatePetResponse;
import com.vet.appointment.system.pet.service.domain.entity.Pet;
import com.vet.appointment.system.pet.service.domain.event.PetCreatedEvent;
import com.vet.appointment.system.pet.service.domain.exception.PetDomainException;
import com.vet.appointment.system.pet.service.domain.mapper.PetDataMapper;
import com.vet.appointment.system.pet.service.domain.outbox.scheduler.appointment.AppointmentOutboxHelper;
import com.vet.appointment.system.pet.service.domain.ports.output.repository.PetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class CreatePetCommandHandler {

    private final PetDomainService petDomainService;
    private final PetDataMapper petDataMapper;
    private final PetRepository petRepository;
    private final AppointmentOutboxHelper appointmentOutboxHelper;

    public CreatePetCommandHandler(PetDomainService petDomainService,
                                   PetDataMapper petDataMapper,
                                   PetRepository petRepository,
                                   AppointmentOutboxHelper appointmentOutboxHelper) {
        this.petDomainService = petDomainService;
        this.petDataMapper = petDataMapper;
        this.petRepository = petRepository;
        this.appointmentOutboxHelper = appointmentOutboxHelper;
    }

    @Transactional
    public CreatePetResponse createPetFromCommand(CreatePetCommand createPetCommand) {
        Pet pet = petDataMapper.createPetCommandToPet(createPetCommand);

        PetCreatedEvent petCreatedEvent = petDomainService.validateAndInitiatePet(pet);
        Pet savedPet = petRepository.savePet(pet);
        if(savedPet == null) {
            throw new PetDomainException("Could not save pet with owner id: " + pet.getOwnerId());
        }
        appointmentOutboxHelper.saveAppointmentOutboxMessage(
                petDataMapper.petCreatedEventToPetAppointmentEventPayload(petCreatedEvent));
        log.info("Successfully saved pet with id {}", pet.getId().getValue());


        return new CreatePetResponse("Successfully created pet!", 201);
    }
}
