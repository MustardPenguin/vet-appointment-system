package com.vet.appointment.system.appointment.service.domain.helper;

import com.vet.appointment.system.appointment.service.domain.dto.message.AccountModel;
import com.vet.appointment.system.appointment.service.domain.dto.message.PetModel;
import com.vet.appointment.system.appointment.service.domain.entity.Appointment;
import com.vet.appointment.system.appointment.service.domain.exception.AppointmentDomainException;
import com.vet.appointment.system.appointment.service.domain.ports.output.repository.AccountRepository;
import com.vet.appointment.system.appointment.service.domain.ports.output.repository.AppointmentRepository;
import com.vet.appointment.system.appointment.service.domain.ports.output.repository.PetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class AppointmentServiceHelper {

    private final AccountRepository accountRepository;
    private final PetRepository petRepository;
    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceHelper(AccountRepository accountRepository,
                                    PetRepository petRepository,
                                    AppointmentRepository appointmentRepository) {
        this.accountRepository = accountRepository;
        this.petRepository = petRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public AccountModel getAccountById(UUID accountId) {
        Optional<AccountModel> accountModel = accountRepository.findById(accountId);
        if(accountModel.isEmpty()) {
            log.warn("Could not find account with account id: {}", accountId);
            throw new AppointmentDomainException("Could not find account with account id: " + accountId);
        }
        return accountModel.get();
    }

    public PetModel getPetById(UUID petId) {
        Optional<PetModel> petModel = petRepository.findById(petId);
        if(petModel.isEmpty()) {
            log.warn("Could not find pet with pet id: {}", petId);
            throw new AppointmentDomainException("Could not find pet with pet id: " + petId);
        }
        return petModel.get();
    }

    public Appointment getAppointmentById(UUID appointmentId) {
        Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
        if(appointment.isEmpty()) {
            log.warn("Could not find appointment with appointment id: {}", appointmentId);
            throw new AppointmentDomainException("Could not find appointment with appointment id: " + appointmentId);
        }
        return appointment.get();
    }

    public void validateAccountAndPet(UUID accountId, UUID petId) {
        AccountModel accountModel = getAccountById(accountId);
        PetModel petModel = getPetById(petId);
        log.info("Account id: {}", accountModel.getId());
        log.info("Pet id: {}", petModel.getOwnerId());
        if(!petModel.getOwnerId().equals(accountModel.getId())) {
            log.warn("Owner id of pet and account does not match!");
            throw new AppointmentDomainException("Owner id of pet and account does not match!");
        }
    }
}
