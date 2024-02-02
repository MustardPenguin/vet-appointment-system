package com.vet.appointment.system.pet.service.domain.impl;

import com.vet.appointment.system.pet.service.domain.PetDomainService;
import com.vet.appointment.system.pet.service.domain.entity.Pet;
import com.vet.appointment.system.pet.service.domain.event.PetCreatedEvent;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static com.vet.appointment.system.domain.DomainConstants.UTC;

public class PetDomainServiceImpl implements PetDomainService {
    @Override
    public PetCreatedEvent validateAndInitiatePet(Pet pet) {

        return new PetCreatedEvent(pet, ZonedDateTime.now(ZoneId.of(UTC)));
    }
}
