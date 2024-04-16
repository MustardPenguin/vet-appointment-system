package com.vet.appointment.system.pet.service.domain.ports.input.message.listener;

import com.vet.appointment.system.pet.service.domain.entity.Pet;

import java.util.UUID;

public interface PetCreatedMessageListener {

    void petCreated(Pet pet, UUID outboxId);

    void petDeleted(Pet pet, UUID outboxId);
}
