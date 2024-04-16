package com.vet.appointment.system.payment.service.application.rest;

import com.vet.appointment.system.payment.service.domain.dto.response.BalanceResponseBody;
import com.vet.appointment.system.payment.service.domain.dto.response.TransactionsResponseBody;
import com.vet.appointment.system.payment.service.domain.ports.input.PaymentApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class PaymentController {

    private final PaymentApplicationService paymentApplicationService;

    public PaymentController(PaymentApplicationService paymentApplicationService) {
        this.paymentApplicationService = paymentApplicationService;
    }

    @GetMapping("/api/account/{accountId}/balance")
    public ResponseEntity<BalanceResponseBody> getBalanceByAccountId(@PathVariable UUID accountId) {
        return ResponseEntity.ok(paymentApplicationService.getBalanceByAccountId(accountId));
    }

    @GetMapping("/api/account/{accountId}/transaction")
    public ResponseEntity<TransactionsResponseBody> getTransactionsByAccountId(@PathVariable UUID accountId) {
        return ResponseEntity.ok(paymentApplicationService.getTransactionsByAccountId(accountId));
    }
}
