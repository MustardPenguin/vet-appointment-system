package com.vet.appointment.system.messaging.event;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
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
    @JsonProperty
    private ZonedDateTime createdAt;

    public AccountAppointmentEventPayload() {}

    private AccountAppointmentEventPayload(Builder builder) {
        id = builder.id;
        email = builder.email;
        firstName = builder.firstName;
        lastName = builder.lastName;
        createdAt = builder.createdAt;
    }

    public static Builder builder() {
        return new Builder();
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

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public static final class Builder {
        private UUID id;
        private String email;
        private String firstName;
        private String lastName;
        private ZonedDateTime createdAt;

        private Builder() {
        }

        public Builder id(UUID val) {
            id = val;
            return this;
        }

        public Builder email(String val) {
            email = val;
            return this;
        }

        public Builder firstName(String val) {
            firstName = val;
            return this;
        }

        public Builder lastName(String val) {
            lastName = val;
            return this;
        }

        public Builder createdAt(ZonedDateTime val) {
            createdAt = val;
            return this;
        }

        public AccountAppointmentEventPayload build() {
            return new AccountAppointmentEventPayload(this);
        }
    }
}
