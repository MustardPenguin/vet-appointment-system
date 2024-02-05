package com.vet.appointment.system.appointment.service.dataaccess.pet.repository;

import com.vet.appointment.system.appointment.service.dataaccess.pet.entity.PetEntity;
import com.vet.appointment.system.appointment.service.domain.dto.message.PetModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PetJpaRepository extends JpaRepository<PetEntity, UUID> {

    Optional<PetEntity> findById(UUID id);
}
