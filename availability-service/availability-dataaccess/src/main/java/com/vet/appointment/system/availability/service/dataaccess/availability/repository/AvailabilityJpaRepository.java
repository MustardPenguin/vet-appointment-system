package com.vet.appointment.system.availability.service.dataaccess.availability.repository;

import com.vet.appointment.system.availability.service.dataaccess.availability.entity.AvailabilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AvailabilityJpaRepository extends JpaRepository<AvailabilityEntity, UUID> {

    @Query(value = // 900 930 1000
            "SELECT * FROM availability.availabilities " +
                    "WHERE (start_date_time <= :start AND end_date_time >= :end) " +
                    "OR (start_date_time > :start AND start_date_time < :end) " +
                    "OR (end_date_time < :end AND end_date_time > :start) ", nativeQuery = true)
    List<AvailabilityEntity>
        getAvailabilityEntitiesByAppointmentStartDateTimeAfterAndAppointmentEndDateTimeBefore(
            @Param("start") LocalDateTime appointmentStartDateTime, @Param("end") LocalDateTime appointmentEndDateTime);
}
