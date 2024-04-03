package com.vet.appointment.system.pet.service.messaging.mapper;

import com.vet.appointment.system.messaging.event.PetCreatedEventPayload;
import com.vet.appointment.system.pet.service.domain.entity.Pet;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PetMessagingDataMapper {

    public Pet petCreatedEventPayloadToPet(PetCreatedEventPayload petCreatedEventPayload) {
        return Pet.builder()
                .id(UUID.fromString(petCreatedEventPayload.getId()))
                .name(petCreatedEventPayload.getName())
                .ownerId(UUID.fromString(petCreatedEventPayload.getOwnerId()))
                .birthDate(petCreatedEventPayload.getBirthDate())
                .species(petCreatedEventPayload.getSpecies())
                .build();
    }
}
