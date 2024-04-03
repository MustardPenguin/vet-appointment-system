package com.vet.appointment.system.pet.service.dataaccess.pet.adapter;

import com.vet.appointment.system.pet.service.dataaccess.pet.entity.PetEntity;
import com.vet.appointment.system.pet.service.dataaccess.pet.mapper.PetDataAccessMapper;
import com.vet.appointment.system.pet.service.dataaccess.pet.repository.PetJpaRepository;
import com.vet.appointment.system.pet.service.domain.entity.Pet;
import com.vet.appointment.system.pet.service.domain.ports.output.repository.PetRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PetRepositoryImpl implements PetRepository {

    private final PetDataAccessMapper petDataAccessMapper;
    private final PetJpaRepository petJpaRepository;

    public PetRepositoryImpl(PetDataAccessMapper petDataAccessMapper, PetJpaRepository petJpaRepository) {
        this.petDataAccessMapper = petDataAccessMapper;
        this.petJpaRepository = petJpaRepository;
    }


    @Override
    public Pet savePet(Pet pet) {
        PetEntity petEntity = petDataAccessMapper.petToPetEntity(pet);
        return petDataAccessMapper.petEntityToPet(petJpaRepository.save(petEntity));
    }

    @Override
    public Pet getPetById(UUID id) {
        return petDataAccessMapper.petEntityToPet(petJpaRepository.findPetEntityById(id).get());
    }
}
