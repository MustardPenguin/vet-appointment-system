package com.vet.appointment.system.payment.service.domain.dto.response;

import com.vet.appointment.system.payment.service.domain.dto.model.TransactionModel;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public class TransactionsResponseBody extends ResponseEntity<List<TransactionModel>> {
    public TransactionsResponseBody(List<TransactionModel> transactions, HttpStatusCode status) {
        super(transactions, status);
    }
}
