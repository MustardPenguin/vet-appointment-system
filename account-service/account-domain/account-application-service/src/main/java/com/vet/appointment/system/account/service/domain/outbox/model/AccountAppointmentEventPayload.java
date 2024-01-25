package com.vet.appointment.system.account.service.domain.outbox.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class AccountAppointmentEventPayload {

    @JsonProperty
    private UUID id;
    @JsonProperty
    private String email;
    @JsonProperty
    private String firstName;
    @JsonProperty
    private String lastName;

    public AccountAppointmentEventPayload(UUID id, String email, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
