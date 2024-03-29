package com.vet.appointment.system.availability.service.dataaccess.availability.mapper;

import com.vet.appointment.system.availability.service.dataaccess.availability.entity.AvailabilityEntity;
import com.vet.appointment.system.availability.service.domain.entity.Availability;
import org.springframework.stereotype.Component;

@Component
public class AvailabilityDataAccessMapper {

    public Availability availabilityEntityToAvailability(AvailabilityEntity availabilityEntity) {
        return Availability.builder()
                .id(availabilityEntity.getId())
                .startDateTime(availabilityEntity.getStartDateTime())
                .endDateTime(availabilityEntity.getEndDateTime())
                .reason(availabilityEntity.getReason())
                .build();
    }

    public AvailabilityEntity availabilityToAvailabilityEntity(Availability availability) {
        return AvailabilityEntity.builder()
                .id(availability.getId().getValue())
                .startDateTime(availability.getStartDateTime())
                .endDateTime(availability.getEndDateTime())
                .reason(availability.getReason())
                .build();
    }
}
