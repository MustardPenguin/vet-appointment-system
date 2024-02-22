package com.vet.appointment.system.availability.service.domain.dto.message;

import java.time.LocalDateTime;
import java.util.UUID;

public class AvailabilityRequest {

    private UUID eventId;
    private UUID sagaId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public AvailabilityRequest(UUID eventId, UUID sagaId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.eventId = eventId;
        this.sagaId = sagaId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public UUID getEventId() {
        return eventId;
    }

    public UUID getSagaId() {
        return sagaId;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }
}
