package com.vet.appointment.system.appointment.service.domain.saga;

import com.vet.appointment.system.appointment.service.domain.dto.message.PaymentResponse;
import com.vet.appointment.system.appointment.service.domain.dto.outbox.AppointmentPaymentOutboxMessage;
import com.vet.appointment.system.appointment.service.domain.entity.Appointment;
import com.vet.appointment.system.appointment.service.domain.helper.AppointmentServiceDataHelper;
import com.vet.appointment.system.appointment.service.domain.helper.PaymentOutboxHelper;
import com.vet.appointment.system.domain.valueobject.AppointmentStatus;
import com.vet.appointment.system.domain.valueobject.PaymentStatus;
import com.vet.appointment.system.saga.SagaStatus;
import com.vet.appointment.system.saga.SagaSteps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class AppointmentPaymentSaga implements SagaSteps<PaymentResponse> {

    private final AppointmentServiceDataHelper appointmentServiceDataHelper;
    private final PaymentOutboxHelper paymentOutboxHelper;

    public AppointmentPaymentSaga(AppointmentServiceDataHelper appointmentServiceDataHelper, PaymentOutboxHelper paymentOutboxHelper) {
        this.appointmentServiceDataHelper = appointmentServiceDataHelper;
        this.paymentOutboxHelper = paymentOutboxHelper;
    }

    @Override
    @Transactional
    public void process(PaymentResponse paymentResponse) {
        AppointmentPaymentOutboxMessage appointmentPaymentOutboxMessage =
                paymentOutboxHelper.findPaymentOutboxMessageBySagaIdAndSagaStatus(paymentResponse.getSagaId(), SagaStatus.PROCESSING);
        if(appointmentPaymentOutboxMessage == null) {
            return;
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

    }
}
