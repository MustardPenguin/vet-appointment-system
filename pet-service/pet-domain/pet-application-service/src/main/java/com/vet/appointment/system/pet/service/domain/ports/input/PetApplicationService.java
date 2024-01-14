package com.vet.appointment.system.pet.service.domain.ports.input;

import com.vet.appointment.system.pet.service.domain.dto.create.CreatePetCommand;
import com.vet.appointment.system.pet.service.domain.event.PetCreatedEvent;

public interface PetApplicationService {

    void createPet(CreatePetCommand createPetCommand);
}
