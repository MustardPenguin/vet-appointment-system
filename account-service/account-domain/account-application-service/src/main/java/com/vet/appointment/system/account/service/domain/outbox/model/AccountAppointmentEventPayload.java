package com.vet.appointment.system.account.service.domain.outbox.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class AccountAppointmentEventPayload {

    @JsonProperty
    private UUID id;
    @JsonProperty
    private String email;

    public AccountAppointmentEventPayload(UUID id, String email) {
        this.id = id;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
