package com.vet.appointment.system.pet.service.dataaccess.pet.repository;

import com.vet.appointment.system.pet.service.dataaccess.pet.entity.PetEntity;
import com.vet.appointment.system.pet.service.domain.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PetJpaRepository extends JpaRepository<PetEntity, UUID> {

    Optional<PetEntity> findPetEntityById(UUID id);

    List<PetEntity> findPetEntitiesByOwnerId(UUID ownerId);
}
