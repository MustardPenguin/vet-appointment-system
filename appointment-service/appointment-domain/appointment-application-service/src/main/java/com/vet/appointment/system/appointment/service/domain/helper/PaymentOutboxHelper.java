package com.vet.appointment.system.appointment.service.domain.helper;

import com.vet.appointment.system.appointment.service.domain.dto.outbox.AppointmentPaymentOutboxMessage;
import com.vet.appointment.system.appointment.service.domain.exception.AppointmentDomainException;
import com.vet.appointment.system.appointment.service.domain.ports.output.repository.outbox.PaymentOutboxRepository;
import com.vet.appointment.system.messaging.event.AppointmentPaymentEventPayload;
import com.vet.appointment.system.saga.SagaStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.vet.appointment.system.saga.SagaConstants.APPOINTMENT_SAGA_NAME;

@Slf4j
@Component
public class PaymentOutboxHelper {

    private final PaymentOutboxRepository paymentOutboxRepository;
    private final OutboxHelper<AppointmentPaymentEventPayload> outboxHelper;

    public PaymentOutboxHelper(PaymentOutboxRepository paymentOutboxRepository,
                               OutboxHelper<AppointmentPaymentEventPayload> outboxHelper) {
        this.paymentOutboxRepository = paymentOutboxRepository;
        this.outboxHelper = outboxHelper;
    }

    @Transactional
    public void save(AppointmentPaymentOutboxMessage appointmentPaymentOutboxMessage) {
        AppointmentPaymentOutboxMessage response = paymentOutboxRepository.save(appointmentPaymentOutboxMessage);
        if(response == null) {
            log.error("Could not save AppointmentPaymentOutboxMessage of id: {}", appointmentPaymentOutboxMessage.getId());
            throw new AppointmentDomainException("Could not save AppointmentPaymentOutboxMessage of id: " +
                    appointmentPaymentOutboxMessage.getId());
        }
        log.info("Successfully saved AppointmentPaymentOutboxMessage of id: {}", appointmentPaymentOutboxMessage.getId());
    }

    @Transactional
    public void savePaymentOutboxMessage(AppointmentPaymentEventPayload appointmentPaymentEventPayload,
                                         SagaStatus sagaStatus,
                                         UUID sagaId) {
        save(AppointmentPaymentOutboxMessage.builder()
                .id(UUID.randomUUID())
                .createdAt(appointmentPaymentEventPayload.getCreatedAt())
                .payload(outboxHelper.createPayload(appointmentPaymentEventPayload, appointmentPaymentEventPayload.getAccountId()))
                .sagaStatus(sagaStatus)
                .sagaId(sagaId)
                .sagaType(APPOINTMENT_SAGA_NAME)
                .build());
    }
}
