package com.vet.appointment.system.pet.service.domain.mapper;

import com.vet.appointment.system.pet.service.domain.dto.create.CreatePetCommand;
import com.vet.appointment.system.pet.service.domain.entity.Pet;
import com.vet.appointment.system.pet.service.domain.event.PetCreatedEvent;
import com.vet.appointment.system.pet.service.domain.outbox.model.PetAppointmentEventPayload;
import org.springframework.stereotype.Component;

@Component
public class PetDataMapper {

    public Pet createPetCommandToPet(CreatePetCommand createPetCommand) {
        return Pet.builder()
                .ownerId(createPetCommand.getOwnerId())
                .name(createPetCommand.getName())
                .species(createPetCommand.getSpecies())
                .birthDate(createPetCommand.getBirthDate())
                .build();
    }

    public PetAppointmentEventPayload petCreatedEventToPetAppointmentEventPayload(PetCreatedEvent petCreatedEvent) {
        return PetAppointmentEventPayload.builder()
                .id(petCreatedEvent.getEntity().getId().getValue().toString())
                .ownerId(petCreatedEvent.getEntity().getOwnerId().toString())
                .birthDate(petCreatedEvent.getEntity().getBirthDate())
                .name(petCreatedEvent.getEntity().getName())
                .species(petCreatedEvent.getEntity().getSpecies())
                .createdAt(petCreatedEvent.getCreatedAt())
                .build();
    }
}
