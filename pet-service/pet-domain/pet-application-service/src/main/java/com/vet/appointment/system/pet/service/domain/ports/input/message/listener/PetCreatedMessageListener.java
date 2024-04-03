package com.vet.appointment.system.pet.service.domain.ports.input.message.listener;

import com.vet.appointment.system.pet.service.domain.entity.Pet;

public interface PetCreatedMessageListener {

    void petCreated(Pet pet);
}
