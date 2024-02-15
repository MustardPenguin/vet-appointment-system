package com.vet.appointment.system.availability.service.dataaccess.availability.adapter;

import com.vet.appointment.system.availability.service.dataaccess.availability.entity.AvailabilityEntity;
import com.vet.appointment.system.availability.service.dataaccess.availability.mapper.AvailabilityDataAccessMapper;
import com.vet.appointment.system.availability.service.dataaccess.availability.repository.AvailabilityJpaRepository;
import com.vet.appointment.system.availability.service.domain.entity.Availability;
import com.vet.appointment.system.availability.service.domain.ports.output.repository.AvailabilityRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AvailabilityRepositoryImpl implements AvailabilityRepository {

    private final AvailabilityJpaRepository availabilityJpaRepository;
    private final AvailabilityDataAccessMapper availabilityDataAccessMapper;

    public AvailabilityRepositoryImpl(AvailabilityJpaRepository availabilityJpaRepository,
                                      AvailabilityDataAccessMapper availabilityDataAccessMapper) {
        this.availabilityJpaRepository = availabilityJpaRepository;
        this.availabilityDataAccessMapper = availabilityDataAccessMapper;
    }

    public List<Availability> getAvailabilitiesOnDate(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return availabilityJpaRepository
                .getAvailabilityEntitiesByAppointmentStartDateTimeAfterAndAppointmentEndDateTimeBefore(startDateTime, endDateTime)
                .stream().map(availabilityDataAccessMapper::availabilityEntityToAvailability)
                .collect(Collectors.toList());
    }
}
