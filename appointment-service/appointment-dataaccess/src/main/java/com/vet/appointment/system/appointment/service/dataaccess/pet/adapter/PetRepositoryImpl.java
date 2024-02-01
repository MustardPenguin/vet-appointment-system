package com.vet.appointment.system.appointment.service.dataaccess.pet.adapter;

import com.vet.appointment.system.appointment.service.dataaccess.pet.entity.PetEntity;
import com.vet.appointment.system.appointment.service.dataaccess.pet.mapper.PetDataAccessMapper;
import com.vet.appointment.system.appointment.service.dataaccess.pet.repository.PetJpaRepository;
import com.vet.appointment.system.appointment.service.domain.dto.message.PetModel;
import com.vet.appointment.system.appointment.service.domain.ports.output.repository.PetRepository;
import org.springframework.stereotype.Component;

@Component
public class PetRepositoryImpl implements PetRepository {

    private final PetDataAccessMapper petDataAccessMapper;
    private final PetJpaRepository petJpaRepository;

    public PetRepositoryImpl(PetDataAccessMapper petDataAccessMapper, PetJpaRepository petJpaRepository) {
        this.petDataAccessMapper = petDataAccessMapper;
        this.petJpaRepository = petJpaRepository;
    }

    @Override
    public PetModel save(PetModel petModel) {
        PetEntity petEntity = petJpaRepository
                .save(petDataAccessMapper.petModelToPetEntity(petModel));
        return petDataAccessMapper.petEntityToPetModel(petEntity);
    }
}
