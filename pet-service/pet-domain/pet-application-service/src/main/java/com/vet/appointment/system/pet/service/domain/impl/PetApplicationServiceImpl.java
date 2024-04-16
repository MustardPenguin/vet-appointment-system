package com.vet.appointment.system.pet.service.domain.impl;

import com.vet.appointment.system.domain.dto.ResponseMessage;
import com.vet.appointment.system.pet.service.domain.CreatePetCommandHandler;
import com.vet.appointment.system.pet.service.domain.dto.create.CreatePetCommand;
import com.vet.appointment.system.pet.service.domain.dto.create.CreatePetResponse;
import com.vet.appointment.system.pet.service.domain.dto.create.UpdatePetCommand;
import com.vet.appointment.system.pet.service.domain.entity.Pet;
import com.vet.appointment.system.pet.service.domain.mapper.PetDataMapper;
import com.vet.appointment.system.pet.service.domain.outbox.scheduler.appointment.AppointmentOutboxHelper;
import com.vet.appointment.system.pet.service.domain.ports.input.PetApplicationService;
import com.vet.appointment.system.pet.service.domain.ports.output.repository.PetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class PetApplicationServiceImpl implements PetApplicationService {

    private final CreatePetCommandHandler createPetCommandHandler;
    private final AppointmentOutboxHelper appointmentOutboxHelper;
    private final PetDataMapper petDataMapper;
    private final PetRepository petRepository;

    public PetApplicationServiceImpl(CreatePetCommandHandler createPetCommandHandler,
                                     AppointmentOutboxHelper appointmentOutboxHelper,
                                     PetDataMapper petDataMapper,
                                     PetRepository petRepository) {
        this.createPetCommandHandler = createPetCommandHandler;
        this.appointmentOutboxHelper = appointmentOutboxHelper;
        this.petDataMapper = petDataMapper;
        this.petRepository = petRepository;
    }

    @Override
    public CreatePetResponse createPet(CreatePetCommand createPetCommand) {
        log.info("Creating pet for user id {} at service layer", createPetCommand.getOwnerId());
        return createPetCommandHandler.createPetFromCommand(createPetCommand);
    }

    @Override
    public CreatePetResponse updatePet(UpdatePetCommand updatePetCommand, UUID accountId) {
        log.info("Updating pet for user id {} at service layer", accountId);
        return createPetCommandHandler.updatePetFromCommand(updatePetCommand, accountId);
    }

    @Override
    public CreatePetResponse deletePet(UUID petId, UUID accountId) {
        log.info("Deleting pet of id: {} for user id {} at service layer", petId, accountId);
        return createPetCommandHandler.deletePet(petId, accountId);
    }

    @Override
    public List<Pet> findPetsByOwnerId(UUID ownerId) {
        return petRepository.findPetsByOwnerId(ownerId);
    }
}