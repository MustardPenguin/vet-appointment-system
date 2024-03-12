package com.vet.appointment.system.availability.service.domain.ports.output.repository;

import com.vet.appointment.system.availability.service.domain.entity.Availability;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AvailabilityRepository {

    Optional<List<Availability>> getAvailabilitiesOnDate(LocalDateTime startDateTime, LocalDateTime endDateTime);

    Optional<Availability> findAvailabilityById(UUID id);

    Availability save(Availability availability);

    void delete(Availability availability);
}
