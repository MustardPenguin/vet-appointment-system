package com.vet.appointment.system.payment.service.dataaccess.balance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "balances")
public class BalanceEntity {

    @Id
    private UUID id;
    private UUID accountId;
    private String email;
    private BigDecimal credit;

    public BalanceEntity() {}

    public BalanceEntity(UUID id, UUID accountId, String email, BigDecimal credit) {
        this.id = id;
        this.accountId = accountId;
        this.email = email;
        this.credit = credit;
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
