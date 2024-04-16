package com.vet.appointment.system.pet.service.dataaccess.pet.adapter;

import com.vet.appointment.system.pet.service.dataaccess.pet.entity.PetEntity;
import com.vet.appointment.system.pet.service.dataaccess.pet.mapper.PetDataAccessMapper;
import com.vet.appointment.system.pet.service.dataaccess.pet.repository.PetJpaRepository;
import com.vet.appointment.system.pet.service.domain.entity.Pet;
import com.vet.appointment.system.pet.service.domain.ports.output.repository.PetRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
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
        try {
            Pet savedPet = petDataAccessMapper.petEntityToPet(petJpaRepository.save(petEntity));
            return savedPet;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Pet getPetById(UUID id) {
        Optional<PetEntity> pet = petJpaRepository.findById(id);
        if(pet.isEmpty()) {
            return null;
        }
        return petDataAccessMapper.petEntityToPet(pet.get());
    }

    @Override
    public void deletePet(UUID id) {
        petJpaRepository.deleteById(id);
    }

    @Override
    public List<Pet> findPetsByOwnerId(UUID ownerId) {
        return petJpaRepository.findPetEntitiesByOwnerId(ownerId).stream()
                .map(petDataAccessMapper::petEntityToPet)
                .toList();
    }
}
