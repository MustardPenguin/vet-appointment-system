package com.vet.appointment.system.appointment.service.dataaccess.pet.mapper;

import com.vet.appointment.system.appointment.service.dataaccess.pet.entity.PetEntity;
import com.vet.appointment.system.appointment.service.domain.dto.message.PetModel;
import org.springframework.stereotype.Component;

@Component
public class PetDataAccessMapper {

    public PetEntity petModelToPetEntity(PetModel petModel) {
        return PetEntity.builder()
                .id(petModel.getId())
                .ownerId(petModel.getOwnerId())
                .name(petModel.getName())
                .birthDate(petModel.getBirthDate())
                .species(petModel.getSpecies())
                .build();
    }

    public PetModel petEntityToPetModel(PetEntity petEntity) {
        return PetModel.builder()
                .id(petEntity.getId())
                .ownerId(petEntity.getOwnerId())
                .name(petEntity.getName())
                .birthDate(petEntity.getBirthDate())
                .species(petEntity.getSpecies())
                .build();
    }
}
