package com.vet.appointment.system.pet.service.dataaccess.pet.mapper;

import com.vet.appointment.system.pet.service.dataaccess.pet.entity.PetEntity;
import com.vet.appointment.system.pet.service.domain.entity.Pet;
import org.springframework.stereotype.Component;

@Component
public class PetDataAccessMapper {

    public PetEntity petToPetEntity(Pet pet) {
        return PetEntity.builder()
                .id(pet.getId().getValue())
                .ownerId(pet.getOwnerId())
                .birthDate(pet.getBirthDate())
                .name(pet.getName())
                .species(pet.getSpecies())
                .build();
    }

    public Pet petEntityToPet(PetEntity petEntity) {
        return Pet.builder()
                .ownerId(petEntity.getOwnerId())
                .birthDate(petEntity.getBirthDate())
                .name(petEntity.getName())
                .species(petEntity.getSpecies())
                .build();
    }
}
