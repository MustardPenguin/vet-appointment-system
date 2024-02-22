package com.vet.appointment.system.appointment.service.domain.dto.get;

import java.util.UUID;

public class GetAppointmentQuery {

    private UUID appointmentId;

    public UUID getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(UUID appointmentId) {
        this.appointmentId = appointmentId;
    }
}
