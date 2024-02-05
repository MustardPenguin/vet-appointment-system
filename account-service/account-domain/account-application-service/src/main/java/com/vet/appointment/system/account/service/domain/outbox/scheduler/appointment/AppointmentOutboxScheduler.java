package com.vet.appointment.system.account.service.domain.outbox.scheduler.appointment;

import com.vet.appointment.system.account.service.domain.outbox.model.AccountAppointmentOutboxMessage;
import com.vet.appointment.system.account.service.domain.ports.output.message.publisher.AccountCreatedMessagePublisher;
import com.vet.appointment.system.account.service.domain.ports.output.repository.AppointmentOutboxRepository;
import com.vet.appointment.system.outbox.OutboxScheduler;
import com.vet.appointment.system.outbox.OutboxStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

import static com.vet.appointment.system.domain.DomainConstants.UTC;

@Slf4j
@Component
public class AppointmentOutboxScheduler implements OutboxScheduler {

    private final AppointmentOutboxRepository appointmentOutboxRepository;
    private final AccountCreatedMessagePublisher accountCreatedMessagePublisher;
    private final AppointmentOutboxHelper appointmentOutboxHelper;

    public AppointmentOutboxScheduler(AppointmentOutboxRepository appointmentOutboxRepository,
                                      AccountCreatedMessagePublisher accountCreatedMessagePublisher,
                                      AppointmentOutboxHelper appointmentOutboxHelper) {
        this.appointmentOutboxRepository = appointmentOutboxRepository;
        this.accountCreatedMessagePublisher = accountCreatedMessagePublisher;
        this.appointmentOutboxHelper = appointmentOutboxHelper;
    }


    @Transactional
    @Scheduled(fixedRateString = "${account-service.outbox-scheduler-rate}",
    initialDelayString = "${account-service.outbox-scheduler-delay}")
    @Override
    public void processOutboxMessage() {
        Optional<List<AccountAppointmentOutboxMessage>> optionalOutboxMessages =
                appointmentOutboxRepository.findByOutboxStatus(OutboxStatus.STARTED);

        if(optionalOutboxMessages.isPresent() && optionalOutboxMessages.get().size() > 0) {
            List<AccountAppointmentOutboxMessage> outboxMessages = optionalOutboxMessages.get();
            outboxMessages.forEach(outboxMessage ->
                    accountCreatedMessagePublisher.publish(
                            outboxMessage,
                            this::updateOutboxStatus));
        }
    }

    public void updateOutboxStatus(AccountAppointmentOutboxMessage accountAppointmentOutboxMessage,
                                   OutboxStatus outboxStatus) {
        accountAppointmentOutboxMessage.setOutboxStatus(outboxStatus);
        accountAppointmentOutboxMessage.setProcessedAt(ZonedDateTime.now(ZoneId.of(UTC)));
        appointmentOutboxHelper.save(accountAppointmentOutboxMessage);
        log.info("Updated outbox message status of id: {} to {}",
                accountAppointmentOutboxMessage.getId(), outboxStatus);
    }
}
