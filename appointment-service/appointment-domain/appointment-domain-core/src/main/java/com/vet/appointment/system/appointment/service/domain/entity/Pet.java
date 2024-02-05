package com.vet.appointment.system.appointment.service.domain.entity;

import com.vet.appointment.system.domain.entity.AggregateRoot;
import com.vet.appointment.system.domain.valueobject.PetId;

import java.util.UUID;

public class Pet extends AggregateRoot<PetId> {

    private UUID ownerId;

    public Pet(UUID id, UUID ownerId) {
        super.setId(new PetId(id));
        this.ownerId = ownerId;
    }

    public UUID getOwnerId() {
        return ownerId;
    }
}
