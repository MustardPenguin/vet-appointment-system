package com.vet.appointment.system.pet.service.domain.event;

import com.vet.appointment.system.domain.event.DomainEvent;
import com.vet.appointment.system.pet.service.domain.entity.Pet;

import java.time.ZonedDateTime;

public class PetEvent extends DomainEvent<Pet> {

    public PetEvent(Pet pet, ZonedDateTime createdAt) {
        super(pet, createdAt);
    }
}
