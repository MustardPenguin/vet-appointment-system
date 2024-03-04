package com.vet.appointment.system.payment.service.domain.impl;

import com.vet.appointment.system.domain.valueobject.PaymentStatus;
import com.vet.appointment.system.payment.service.domain.PaymentDomainService;
import com.vet.appointment.system.payment.service.domain.entity.Balance;
import com.vet.appointment.system.payment.service.domain.entity.Payment;
import com.vet.appointment.system.payment.service.domain.event.PaymentEvent;
import com.vet.appointment.system.payment.service.domain.event.PaymentFailedEvent;
import com.vet.appointment.system.payment.service.domain.event.PaymentPaidEvent;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static com.vet.appointment.system.domain.DomainConstants.UTC;

public class PaymentDomainServiceImpl implements PaymentDomainService {
    @Override
    public PaymentEvent validatePaymentRequest(Payment payment, Balance balance, List<String> errorMessages) {
        BigDecimal difference = balance.getCredit().subtract(payment.getCost());
        if(balance.getCredit().compareTo(BigDecimal.ZERO) == -1) {
            errorMessages.add("Balance can not be in the negative!");
        }
        if(difference.compareTo(BigDecimal.ZERO) == -1) {
            errorMessages.add("Not enough credit!");
        }
        if(!payment.getAccountId().equals(balance.getAccountId())) {
            errorMessages.add("Account id does not match with balance id!");
        }

        if(!errorMessages.isEmpty()) {
            payment.setPaymentStatus(PaymentStatus.FAILED);
            return new PaymentFailedEvent(payment, ZonedDateTime.now(ZoneId.of(UTC)), errorMessages);
        }
        balance.subtractCredit(payment.getCost());
        payment.setPaymentStatus(PaymentStatus.PAID);
        return new PaymentPaidEvent(payment, ZonedDateTime.now(ZoneId.of(UTC)), errorMessages);
    }
}
