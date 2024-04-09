package com.vet.appointment.system.appointment.service.domain.saga;

import com.vet.appointment.system.appointment.service.domain.AppointmentDomainService;
import com.vet.appointment.system.appointment.service.domain.dto.message.PaymentResponse;
import com.vet.appointment.system.appointment.service.domain.dto.outbox.AppointmentAvailabilityOutboxMessage;
import com.vet.appointment.system.appointment.service.domain.dto.outbox.AppointmentPaymentOutboxMessage;
import com.vet.appointment.system.appointment.service.domain.entity.Appointment;
import com.vet.appointment.system.appointment.service.domain.event.AppointmentEvent;
import com.vet.appointment.system.appointment.service.domain.helper.AppointmentServiceDataHelper;
import com.vet.appointment.system.appointment.service.domain.helper.AvailabilityOutboxHelper;
import com.vet.appointment.system.appointment.service.domain.helper.PaymentOutboxHelper;
import com.vet.appointment.system.appointment.service.domain.mapper.AppointmentDataMapper;
import com.vet.appointment.system.domain.valueobject.AppointmentStatus;
import com.vet.appointment.system.domain.valueobject.PaymentStatus;
import com.vet.appointment.system.saga.SagaStatus;
import com.vet.appointment.system.saga.SagaSteps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
public class AppointmentPaymentSaga implements SagaSteps<PaymentResponse> {

    private final AppointmentServiceDataHelper appointmentServiceDataHelper;
    private final AppointmentDomainService appointmentDomainService;
    private final AvailabilityOutboxHelper availabilityOutboxHelper;
    private final PaymentOutboxHelper paymentOutboxHelper;
    private final AppointmentDataMapper appointmentDataMapper;

    public AppointmentPaymentSaga(AppointmentServiceDataHelper appointmentServiceDataHelper,
                                  AppointmentDomainService appointmentDomainService,
                                  AvailabilityOutboxHelper availabilityOutboxHelper,
                                  PaymentOutboxHelper paymentOutboxHelper,
                                  AppointmentDataMapper appointmentDataMapper) {
        this.appointmentServiceDataHelper = appointmentServiceDataHelper;
        this.appointmentDomainService = appointmentDomainService;
        this.availabilityOutboxHelper = availabilityOutboxHelper;
        this.paymentOutboxHelper = paymentOutboxHelper;
        this.appointmentDataMapper = appointmentDataMapper;
    }

    @Override
    @Transactional
    public void process(PaymentResponse paymentResponse) {
        AppointmentPaymentOutboxMessage appointmentPaymentOutboxMessage =
                paymentOutboxHelper.findPaymentOutboxMessageBySagaIdAndSagaStatus(paymentResponse.getSagaId(), SagaStatus.PROCESSING);
        if(appointmentPaymentOutboxMessage == null) {
//            return;
        }
        Appointment appointment = appointmentServiceDataHelper.getAppointmentById(paymentResponse.getAppointmentId());
        appointment.setPaymentId(paymentResponse.getPaymentId());
        appointment.confirmAppointment();

        appointmentServiceDataHelper.saveAppointmentEntity(appointment);
        appointmentPaymentOutboxMessage.setSagaStatus(SagaStatus.SUCCEEDED);
        paymentOutboxHelper.save(appointmentPaymentOutboxMessage);
        log.info("Successfully confirmed appointment for appointment id: {}!", paymentResponse.getAppointmentId());
    }

    @Override
    @Transactional
    public void rollback(PaymentResponse paymentResponse) {
        AppointmentPaymentOutboxMessage appointmentPaymentOutboxMessage =
                paymentOutboxHelper.findPaymentOutboxMessageBySagaIdAndSagaStatus(paymentResponse.getSagaId(), SagaStatus.PROCESSING);
        if(appointmentPaymentOutboxMessage == null) {
//            return;
        }
        Appointment appointment = appointmentServiceDataHelper.getAppointmentById(paymentResponse.getAppointmentId());
        AppointmentEvent appointmentEvent = appointmentDomainService.initiateAppointmentCancelled(appointment, paymentResponse.getErrorMessages());
        appointmentServiceDataHelper.saveAppointmentEntity(appointment);
        appointmentPaymentOutboxMessage.setSagaStatus(SagaStatus.COMPENSATED);
        paymentOutboxHelper.save(appointmentPaymentOutboxMessage);
        log.info("Payment failed for appointment id: {}, performing compensating transactions!", paymentResponse.getAppointmentId());

        Optional<AppointmentAvailabilityOutboxMessage> appointmentAvailabilityOutboxMessageOptional =
                availabilityOutboxHelper.findAvailabilityOutboxMessageBySagaIdAndSagaStatus(paymentResponse.getSagaId(), SagaStatus.SUCCEEDED);
        if(appointmentAvailabilityOutboxMessageOptional.isEmpty()) {
            log.info("Unable to find appointment availability outbox message for saga id: {} and appointment id: {}!",
                    paymentResponse.getSagaId(), appointment.getId().getValue());
            return;
        }

        availabilityOutboxHelper.saveAvailabilityOutboxMessage(
                appointmentDataMapper.appointmentEventToEventPayload(appointmentEvent),
                SagaStatus.COMPENSATING,
                paymentResponse.getSagaId());
    }
}
