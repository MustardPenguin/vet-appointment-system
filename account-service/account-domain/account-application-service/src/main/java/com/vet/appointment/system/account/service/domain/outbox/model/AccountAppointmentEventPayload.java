package com.vet.appointment.system.account.service.domain.outbox.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountAppointmentEventPayload {

    @JsonProperty
    private String id;
    @JsonProperty
    private String email;

    public AccountAppointmentEventPayload(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
