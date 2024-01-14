package com.vet.appointment.system.pet.service.domain.mapper;

import com.vet.appointment.system.pet.service.domain.dto.create.CreatePetCommand;
import com.vet.appointment.system.pet.service.domain.entity.Pet;
import org.springframework.stereotype.Component;

@Component
public class PetDataMapper {

    public Pet createPetCommandToPet(CreatePetCommand createPetCommand) {
        return Pet.builder()
                .ownerId(createPetCommand.getOwnerId())
                .name(createPetCommand.getName())
                .species(createPetCommand.getSpecies())
                .birthDate(createPetCommand.getBirthDate())
                .build();
    }
}
