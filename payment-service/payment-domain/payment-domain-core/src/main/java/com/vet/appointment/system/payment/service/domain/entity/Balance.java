package com.vet.appointment.system.payment.service.domain.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class Balance {

    private UUID id;
    private UUID accountId;
    private String email;
    private BigDecimal credit;

    public Balance(UUID id, UUID accountId, String email, BigDecimal credit) {
        this.id = id;
        this.accountId = accountId;
        this.email = email;
        this.credit = credit;
    }

    public BigDecimal subtractCredit(BigDecimal amount) {
        BigDecimal difference = credit.subtract(amount);
        credit = difference;
        return difference;
    }

    public UUID getId() {
        return id;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public String getEmail() {
        return email;
    }

    public BigDecimal getCredit() {
        return credit;
    }
}
