package com.vet.appointment.system.pet.service.domain.ports.input;

import com.vet.appointment.system.domain.dto.ResponseMessage;
import com.vet.appointment.system.pet.service.domain.dto.create.CreatePetCommand;
import com.vet.appointment.system.pet.service.domain.dto.create.CreatePetResponse;
import com.vet.appointment.system.pet.service.domain.dto.create.UpdatePetCommand;
import com.vet.appointment.system.pet.service.domain.entity.Pet;
import com.vet.appointment.system.pet.service.domain.event.PetCreatedEvent;

import java.util.List;
import java.util.UUID;

public interface PetApplicationService {

    CreatePetResponse createPet(CreatePetCommand createPetCommand);

    CreatePetResponse updatePet(UpdatePetCommand updatePetCommand, UUID accountId);

    CreatePetResponse deletePet(UUID petId, UUID accountId);

    List<Pet> findPetsByOwnerId(UUID ownerId);
}
