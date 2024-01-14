package com.vet.appointment.system.pet.service.domain.ports.output;

import com.vet.appointment.system.pet.service.domain.entity.Pet;

public interface PetRepository {

    Pet savePet(Pet pet);
}
