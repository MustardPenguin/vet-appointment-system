package com.vet.appointment.system.pet.service.domain.mapper;

import com.vet.appointment.system.messaging.event.PetCreatedEventPayload;
import com.vet.appointment.system.pet.service.domain.dto.create.CreatePetCommand;
import com.vet.appointment.system.pet.service.domain.dto.create.UpdatePetCommand;
import com.vet.appointment.system.pet.service.domain.entity.Pet;
import com.vet.appointment.system.pet.service.domain.event.PetCreatedEvent;
import com.vet.appointment.system.pet.service.domain.event.PetEvent;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PetDataMapper {

    public Pet createPetCommandToPet(CreatePetCommand createPetCommand) {
        return Pet.builder()
                .id(UUID.randomUUID())
                .ownerId(createPetCommand.getOwnerId())
                .name(createPetCommand.getName())
                .species(createPetCommand.getSpecies())
                .birthDate(createPetCommand.getBirthDate())
                .build();
    }

    public Pet updatePetCommandToPet(UpdatePetCommand updatePetCommand, UUID accountId) {
        return Pet.builder()
                .id(updatePetCommand.getId())
                .ownerId(accountId)
                .name(updatePetCommand.getName())
                .species(updatePetCommand.getSpecies())
                .birthDate(updatePetCommand.getBirthDate())
                .build();
    }

    public PetCreatedEventPayload petCreatedEventToPetAppointmentEventPayload(PetCreatedEvent petCreatedEvent) {
        return PetCreatedEventPayload.builder()
                .id(petCreatedEvent.getEntity().getId().getValue().toString())
                .ownerId(petCreatedEvent.getEntity().getOwnerId().toString())
                .birthDate(petCreatedEvent.getEntity().getBirthDate())
                .name(petCreatedEvent.getEntity().getName())
                .species(petCreatedEvent.getEntity().getSpecies())
                .createdAt(petCreatedEvent.getCreatedAt())
                .build();
    }

    public PetCreatedEventPayload petCreatedEventToPetAppointmentEventPayload(PetEvent petEvent, String propagationType) {
        return PetCreatedEventPayload.builder()
                .id(petEvent.getEntity().getId().getValue().toString())
                .ownerId(petEvent.getEntity().getOwnerId().toString())
                .birthDate(petEvent.getEntity().getBirthDate())
                .name(petEvent.getEntity().getName())
                .species(petEvent.getEntity().getSpecies())
                .createdAt(petEvent.getCreatedAt())
                .propagationType(propagationType)
                .build();
    }
}
