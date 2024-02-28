package com.vet.appointment.system.availability.service.domain.dto.message;

import java.time.LocalDateTime;
import java.util.UUID;

public class AvailabilityRequest {

    private UUID sagaId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String reason;

    public AvailabilityRequest(UUID sagaId, LocalDateTime startDateTime, LocalDateTime endDateTime, String reason) {
        this.sagaId = sagaId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.reason = reason;
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

    public String getReason() {
        return reason;
    }
}
