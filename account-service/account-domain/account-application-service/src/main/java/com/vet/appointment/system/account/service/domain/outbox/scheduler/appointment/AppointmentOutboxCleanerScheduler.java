package com.vet.appointment.system.account.service.domain.outbox.scheduler.appointment;

import com.vet.appointment.system.account.service.domain.outbox.model.AccountAppointmentOutboxMessage;
import com.vet.appointment.system.account.service.domain.ports.output.repository.AppointmentOutboxRepository;
import com.vet.appointment.system.outbox.OutboxScheduler;
import com.vet.appointment.system.outbox.OutboxStatus;
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
        Optional<List<AccountAppointmentOutboxMessage>> accountAppointmentOutboxMessages =
                appointmentOutboxRepository.findByOutboxStatus(OutboxStatus.COMPLETED);
        if(accountAppointmentOutboxMessages.isPresent() && accountAppointmentOutboxMessages.get().size() > 0) {
            List<AccountAppointmentOutboxMessage> messages = accountAppointmentOutboxMessages.get();
            appointmentOutboxRepository.deleteAppointmentOutboxEntitiesByOutboxStatus(OutboxStatus.COMPLETED);
            log.info("Cleaned up account outbox table, deleting {} outbox messages", messages.size());
        }
    }
}