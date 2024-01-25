package com.vet.appointment.system.account.service.domain.outbox.scheduler.appointment;

import com.vet.appointment.system.account.service.domain.ports.output.repository.AppointmentOutboxRepository;
import com.vet.appointment.system.outbox.OutboxScheduler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class AppointmentOutboxScheduler implements OutboxScheduler {

    private final AppointmentOutboxRepository appointmentOutboxRepository;

    public AppointmentOutboxScheduler(AppointmentOutboxRepository appointmentOutboxRepository) {
        this.appointmentOutboxRepository = appointmentOutboxRepository;
    }


    @Transactional
    @Scheduled(fixedRateString = "${account-service.outbox-scheduler-rate}",
    initialDelayString = "${account-service.outbox-scheduler-delay}")
    @Override
    public void processOutboxMessage() {
        log.info("10 sec");
    }
}
