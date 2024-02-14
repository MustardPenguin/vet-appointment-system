package com.vet.appointment.system.availability.service.domain.valueobject;

import com.vet.appointment.system.availability.service.domain.entity.Availability;
import com.vet.appointment.system.domain.valueobject.BaseId;

import java.util.UUID;

public class AvailabilityId extends BaseId<UUID> {

    public AvailabilityId(UUID value) {
        super(value);
    }
}
