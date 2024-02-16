package com.vet.appointment.system.availability.service.dataaccess.availability.mapper;

import com.vet.appointment.system.availability.service.dataaccess.availability.entity.AvailabilityEntity;
import com.vet.appointment.system.availability.service.domain.entity.Availability;
import org.springframework.stereotype.Component;

@Component
public class AvailabilityDataAccessMapper {

    public Availability availabilityEntityToAvailability(AvailabilityEntity availabilityEntity) {
        return Availability.builder()
                .id(availabilityEntity.getId())
                .eventId(availabilityEntity.getEventId())
                .startDateTime(availabilityEntity.getStartDateTime())
                .endDateTime(availabilityEntity.getEndDateTime())
                .reason(availabilityEntity.getReason())
                .build();
    }
}
