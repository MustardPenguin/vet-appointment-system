package com.vet.appointment.system.availability.service.domain.ports.output.repository;

import com.vet.appointment.system.availability.service.domain.entity.Availability;

import java.time.LocalDateTime;
import java.util.List;

public interface AvailabilityRepository {

    List<Availability> getAvailabilitiesOnDate(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
