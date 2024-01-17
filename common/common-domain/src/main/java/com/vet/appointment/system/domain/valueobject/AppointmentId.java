package com.vet.appointment.system.domain.valueobject;

import java.util.UUID;

public class AppointmentId extends BaseId<UUID> {
    public AppointmentId(UUID value) {
        super(value);
    }
}
