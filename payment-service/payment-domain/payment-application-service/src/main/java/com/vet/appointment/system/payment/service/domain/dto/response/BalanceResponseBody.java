package com.vet.appointment.system.payment.service.domain.dto.response;

import com.vet.appointment.system.payment.service.domain.entity.Balance;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class BalanceResponseBody extends ResponseEntity<Balance> {
    public BalanceResponseBody(Balance body, HttpStatusCode status) {
        super(body, status);
    }
}
