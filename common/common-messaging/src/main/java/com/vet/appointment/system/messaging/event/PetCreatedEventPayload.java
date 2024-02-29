package com.vet.appointment.system.messaging.event;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.ZonedDateTime;

public class PetCreatedEventPayload {

    @JsonProperty
    private String id;
    @JsonProperty
    private String ownerId;
    @JsonProperty
    private String name;
    @JsonProperty
    private String species;
    @JsonProperty
    private LocalDate birthDate;
    @JsonProperty
    private ZonedDateTime createdAt;

    public PetCreatedEventPayload() {}

    private PetCreatedEventPayload(Builder builder) {
        id = builder.id;
        ownerId = builder.ownerId;
        name = builder.name;
        species = builder.species;
        birthDate = builder.birthDate;
        createdAt = builder.createdAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public static final class Builder {
        private String id;
        private String ownerId;
        private String name;
        private String species;
        private LocalDate birthDate;
        private ZonedDateTime createdAt;

        private Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder ownerId(String val) {
            ownerId = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder species(String val) {
            species = val;
            return this;
        }

        public Builder birthDate(LocalDate val) {
            birthDate = val;
            return this;
        }

        public Builder createdAt(ZonedDateTime val) {
            createdAt = val;
            return this;
        }

        public PetCreatedEventPayload build() {
            return new PetCreatedEventPayload(this);
        }
    }
}
