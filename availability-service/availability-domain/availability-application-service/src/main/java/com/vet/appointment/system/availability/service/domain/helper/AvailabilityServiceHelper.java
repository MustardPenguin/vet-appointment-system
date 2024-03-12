package com.vet.appointment.system.availability.service.domain.helper;

import com.vet.appointment.system.availability.service.domain.AvailabilityDomainService;
import com.vet.appointment.system.availability.service.domain.dto.message.AvailabilityRequest;
import com.vet.appointment.system.availability.service.domain.entity.Availability;
import com.vet.appointment.system.availability.service.domain.event.AvailabilityEvent;
import com.vet.appointment.system.availability.service.domain.mapper.AvailabilityDataMapper;
import com.vet.appointment.system.availability.service.domain.ports.output.repository.AvailabilityRepository;
import com.vet.appointment.system.availability.service.domain.valueobject.AvailabilityStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class AvailabilityServiceHelper {

    private final AvailabilityDomainService availabilityDomainService;
    private final AvailabilityDataMapper availabilityDataMapper;
    private final AvailabilityRepository availabilityRepository;

    public AvailabilityServiceHelper(AvailabilityDomainService availabilityDomainService,
                                     AvailabilityDataMapper availabilityDataMapper,
                                     AvailabilityRepository availabilityRepository) {
        this.availabilityDomainService = availabilityDomainService;
        this.availabilityDataMapper = availabilityDataMapper;
        this.availabilityRepository = availabilityRepository;
    }

    public AvailabilityEvent checkAvailability(AvailabilityRequest availabilityRequest) {
        Availability availability = availabilityDataMapper.availabilityRequestToAvailability(availabilityRequest);

        Optional<List<Availability>> optionalAvailabilities = availabilityRepository.getAvailabilitiesOnDate(availability.getStartDateTime(), availability.getEndDateTime());
        List<String> errorMessages = new ArrayList<>();
        AvailabilityEvent availabilityEvent = availabilityDomainService
                .validateAvailability(availability, optionalAvailabilities, errorMessages);

        return availabilityEvent;
    }

    public AvailabilityEvent cancelAvailability(AvailabilityRequest availabilityRequest) {
        Availability availability = availabilityDataMapper.availabilityRequestToAvailability(availabilityRequest);
        List<String> errorMessages = new ArrayList<>();
        AvailabilityEvent availabilityEvent = availabilityDomainService
                .cancelAvailability(availability, errorMessages);
        return availabilityEvent;
    }
}
