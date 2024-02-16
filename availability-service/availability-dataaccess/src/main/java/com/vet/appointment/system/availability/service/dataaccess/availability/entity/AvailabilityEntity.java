package com.vet.appointment.system.availability.service.dataaccess.availability.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "availabilities")
public class AvailabilityEntity {

    @Id
    private UUID id;
    private UUID eventId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String reason;

    public AvailabilityEntity() {}

    public AvailabilityEntity(UUID id, UUID eventId, LocalDateTime startDateTime, LocalDateTime endDateTime, String reason) {
        this.id = id;
        this.eventId = eventId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.reason = reason;
    }

    public UUID getId() {
        return id;
    }

    public UUID getEventId() {
        return eventId;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public String getReason() {
        return reason;
    }
}
