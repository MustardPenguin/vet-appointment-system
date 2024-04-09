package com.vet.appointment.system.availability.service.dataaccess.availability.repository;

import com.vet.appointment.system.availability.service.dataaccess.availability.entity.AvailabilityEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AvailabilityJpaRepository extends JpaRepository<AvailabilityEntity, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Transactional
//    @Query(value =
//            "SELECT * FROM availability.availabilities " +
//                    "WHERE (start_date_time <= :start AND end_date_time >= :end) " +
//                    "OR (start_date_time > :start AND start_date_time < :end) " +
//                    "OR (end_date_time < :end AND end_date_time > :start) ", nativeQuery = true)
    @Query("SELECT a FROM AvailabilityEntity a WHERE " +
            "(a.startDateTime <= :start AND a.endDateTime >= :end) " +
            "OR (a.startDateTime > :start AND a.startDateTime < :end) " +
            "OR (a.endDateTime < :end AND a.endDateTime > :start)")
    Optional<List<AvailabilityEntity>>
        getAvailabilityEntityBetween(@Param("start") LocalDateTime appointmentStartDateTime, @Param("end") LocalDateTime appointmentEndDateTime);

    Optional<AvailabilityEntity> findAvailabilityEntityById(UUID availabilityId);
}
