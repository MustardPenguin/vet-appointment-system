package com.vet.appointment.system.appointment.service.domain.impl;

import com.vet.appointment.system.appointment.service.domain.dto.message.PaymentResponse;
import com.vet.appointment.system.appointment.service.domain.dto.outbox.AppointmentPaymentOutboxMessage;
import com.vet.appointment.system.appointment.service.domain.helper.AppointmentServiceDataHelper;
import com.vet.appointment.system.appointment.service.domain.helper.PaymentOutboxHelper;
import com.vet.appointment.system.appointment.service.domain.ports.input.message.listener.PaymentResponseMessageListener;
import com.vet.appointment.system.appointment.service.domain.saga.AppointmentPaymentSaga;
import com.vet.appointment.system.saga.SagaStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class PaymentResponseMessageListenerImpl implements PaymentResponseMessageListener {

    private final AppointmentPaymentSaga appointmentPaymentSaga;

    public PaymentResponseMessageListenerImpl(AppointmentPaymentSaga appointmentPaymentSaga) {
        this.appointmentPaymentSaga = appointmentPaymentSaga;
    }

    @Override
    public void paymentPaid(PaymentResponse paymentResponse) {
        appointmentPaymentSaga.process(paymentResponse);
    }

    @Override
    public void paymentFailed(PaymentResponse paymentResponse) {
        appointmentPaymentSaga.rollback(paymentResponse);
    }
}
