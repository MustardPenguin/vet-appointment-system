package com.vet.appointment.system.pet.service.domain.impl;

import com.vet.appointment.system.pet.service.domain.CreatePetCommandHandler;
import com.vet.appointment.system.pet.service.domain.PetDomainService;
import com.vet.appointment.system.pet.service.domain.dto.create.CreatePetCommand;
import com.vet.appointment.system.pet.service.domain.dto.create.CreatePetResponse;
import com.vet.appointment.system.pet.service.domain.entity.Pet;
import com.vet.appointment.system.pet.service.domain.event.PetCreatedEvent;
import com.vet.appointment.system.pet.service.domain.mapper.PetDataMapper;
import com.vet.appointment.system.pet.service.domain.ports.input.PetApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PetApplicationServiceImpl implements PetApplicationService {

    private final CreatePetCommandHandler createPetCommandHandler;

    public PetApplicationServiceImpl(CreatePetCommandHandler createPetCommandHandler) {
        this.createPetCommandHandler = createPetCommandHandler;
    }


    @Override
    public CreatePetResponse createPet(CreatePetCommand createPetCommand) {
        log.info("Creating pet for user id {} at service layer", createPetCommand.getOwnerId());

        PetCreatedEvent petCreatedEvent = createPetCommandHandler.createPetFromCommand(createPetCommand);

        return new CreatePetResponse("Successfully created pet!", 201);
    }
}
