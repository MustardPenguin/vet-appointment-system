package com.vet.appointment.system.pet.service.domain;

import com.vet.appointment.system.pet.service.domain.dto.create.CreatePetCommand;
import com.vet.appointment.system.pet.service.domain.dto.create.CreatePetResponse;
import com.vet.appointment.system.pet.service.domain.dto.create.UpdatePetCommand;
import com.vet.appointment.system.pet.service.domain.entity.Pet;
import com.vet.appointment.system.pet.service.domain.event.PetCreatedEvent;
import com.vet.appointment.system.pet.service.domain.event.PetEvent;
import com.vet.appointment.system.pet.service.domain.exception.PetDomainException;
import com.vet.appointment.system.pet.service.domain.mapper.PetDataMapper;
import com.vet.appointment.system.pet.service.domain.outbox.scheduler.appointment.AppointmentOutboxHelper;
import com.vet.appointment.system.pet.service.domain.ports.output.repository.PetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import static com.vet.appointment.system.domain.DomainConstants.UTC;

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

        return new CreatePetResponse("Successfully created pet with id " + pet.getId().getValue() + "!", 201);
    }

    @Transactional
    public CreatePetResponse updatePetFromCommand(UpdatePetCommand updatePetCommand, UUID accountId) {
        Pet savedPet = petRepository.getPetById(updatePetCommand.getId());
        if(savedPet == null) {
            log.error("Pet with id {} does not exist!", updatePetCommand.getId());
            return new CreatePetResponse("Pet with id " + updatePetCommand.getId() + " does not exist!", 400);
        }
        log.info("Owner id: {}, Account id: {}, Equal: {}", savedPet.getOwnerId(), accountId, savedPet.getOwnerId().equals(accountId));
        if(!accountId.equals(savedPet.getOwnerId())) {
            log.error("Owner id in the command does not match the owner id in the pet object!");
            return new CreatePetResponse("You must own the pet in order to update it!", 400);
        }

        Pet pet = petDataMapper.updatePetCommandToPet(updatePetCommand, accountId);
        PetCreatedEvent petCreatedEvent = petDomainService.validateAndInitiatePet(pet);

        Pet response = petRepository.savePet(pet);
        if(response == null) {
            log.error("Could not update pet with id {} with owner id: {}", pet.getId().getValue(), pet.getOwnerId());
            throw new PetDomainException("Could not update pet with id " + pet.getId() + "with owner id: " + pet.getOwnerId());
        }
        appointmentOutboxHelper.saveAppointmentOutboxMessage(
                petDataMapper.petCreatedEventToPetAppointmentEventPayload(petCreatedEvent));
        log.info("Successfully updated pet with id {}", pet.getId().getValue());

        return new CreatePetResponse("Successfully updated pet!", 201);
    }

    @Transactional
    public CreatePetResponse deletePet(UUID petId, UUID accountId) {
        Pet savedPet = petRepository.getPetById(petId);
        if(savedPet == null) {
            log.error("Pet with id {} does not exist!", petId);
            return new CreatePetResponse("Pet with id " + petId + " does not exist!", 404);
        }
        log.info("Owner id: {}, Account id: {}, Equal: {}", savedPet.getOwnerId(), accountId, savedPet.getOwnerId().equals(accountId));
        if(!accountId.equals(savedPet.getOwnerId())) {
            log.error("Owner id in the command does not match the owner id in the pet object!");
            return new CreatePetResponse("You must own the pet in order to delete it!", 400);
        }

        PetEvent petEvent = new PetEvent(savedPet, ZonedDateTime.now(ZoneId.of(UTC)));

        appointmentOutboxHelper.saveAppointmentOutboxMessage(
                petDataMapper.petCreatedEventToPetAppointmentEventPayload(petEvent, "D"));

        petRepository.deletePet(petId);
        log.info("Successfully deleted pet with id {}", petId);

        return new CreatePetResponse("Successfully deleted pet!", 201);
    }
}
