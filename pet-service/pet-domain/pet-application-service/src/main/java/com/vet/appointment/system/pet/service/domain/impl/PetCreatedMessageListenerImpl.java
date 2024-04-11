package com.vet.appointment.system.pet.service.domain.impl;

import com.vet.appointment.system.pet.service.domain.entity.Pet;
import com.vet.appointment.system.pet.service.domain.exception.PetDomainException;
import com.vet.appointment.system.pet.service.domain.ports.input.message.listener.PetCreatedMessageListener;
import com.vet.appointment.system.pet.service.domain.ports.output.repository.PetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class PetCreatedMessageListenerImpl implements PetCreatedMessageListener {

    private final PetRepository petRepository;

    public PetCreatedMessageListenerImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public void petCreated(Pet pet) {
        log.info("Successfully received propagation event for pet id: {} and owner id: {}", pet.getId().getValue(), pet.getOwnerId());

        Pet existingPet = petRepository.getPetById(pet.getId().getValue());
        if(existingPet != null) {
            log.info("Pet with id: {} already exists in the database, either this instance is the source of message or it received a duplicate message!", pet.getId().getValue());
            return;
        }
        Pet response = petRepository.savePet(pet);
        if(response == null) {
            log.error("Could not save pet with pet id: {} and owner id: {}", pet.getId().getValue(), pet.getOwnerId());
            throw new PetDomainException("Could not save pet with pet id: " + pet.getId().getValue() + " and owner id: " + pet.getOwnerId());
        }
        log.info("Successfully saved pet with pet id: {} and owner id: {}", pet.getId().getValue(), pet.getOwnerId());
    }
}
