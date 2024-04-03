package com.vet.appointment.system.pet.service.domain.ports.output.repository;

import com.vet.appointment.system.pet.service.domain.entity.Pet;

import java.util.UUID;

public interface PetRepository {

    Pet savePet(Pet pet);

    Pet getPetById(UUID id);
}
