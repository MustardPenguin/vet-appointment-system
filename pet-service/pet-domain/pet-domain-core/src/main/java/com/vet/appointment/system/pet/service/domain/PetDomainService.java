package com.vet.appointment.system.pet.service.domain;

import com.vet.appointment.system.pet.service.domain.entity.Pet;
import com.vet.appointment.system.pet.service.domain.event.PetCreatedEvent;

public interface PetDomainService {

    PetCreatedEvent validateAndCreatePet(Pet pet);
}
