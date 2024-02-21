package com.vet.appointment.system.pet.service.domain.outbox.scheduler.appointment;

import com.vet.appointment.system.outbox.OutboxScheduler;
import com.vet.appointment.system.outbox.OutboxStatus;
import com.vet.appointment.system.pet.service.domain.dto.outbox.PetAppointmentOutboxMessage;
import com.vet.appointment.system.pet.service.domain.ports.output.repository.AppointmentOutboxRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class AppointmentOutboxCleanerScheduler implements OutboxScheduler {

    private final AppointmentOutboxRepository appointmentOutboxRepository;

    public AppointmentOutboxCleanerScheduler(AppointmentOutboxRepository appointmentOutboxRepository) {
        this.appointmentOutboxRepository = appointmentOutboxRepository;
    }

    @Override
    @Scheduled(cron = "@midnight")
    public void processOutboxMessage() {
        Optional<List<PetAppointmentOutboxMessage>> petAppointmentOutboxMessages
                = appointmentOutboxRepository.findByOutboxStatus(OutboxStatus.COMPLETED);
        if(petAppointmentOutboxMessages.isPresent() && petAppointmentOutboxMessages.get().size() > 0) {
            List<PetAppointmentOutboxMessage> messages = petAppointmentOutboxMessages.get();
            appointmentOutboxRepository.deleteAppointmentOutboxEntitiesByOutboxStatus(OutboxStatus.COMPLETED);
            log.info("Cleaned up pet outbox table, deleting {} outbox messages", messages.size());
        }
    }
}
