package com.vet.appointment.system.domain.valueobject;

import java.util.UUID;

public class PetId extends BaseId<UUID> {
    public PetId(UUID value) {
        super(value);
    }
}
