package com.vet.appointment.system.payment.service.domain;

import com.vet.appointment.system.payment.service.domain.entity.Balance;
import com.vet.appointment.system.payment.service.domain.entity.Payment;
import com.vet.appointment.system.payment.service.domain.event.PaymentEvent;

import java.util.List;

public interface PaymentDomainService {

    PaymentEvent validatePaymentRequest(Payment payment, Balance balance, List<String> errorMessages);
}
